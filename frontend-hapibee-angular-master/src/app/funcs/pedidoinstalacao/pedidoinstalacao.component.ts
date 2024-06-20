import {Component, OnInit} from '@angular/core';
import * as Rellax from 'rellax';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Apiario} from "../apiario";
import { Router } from '@angular/router';
import {catchError, Observable, of} from "rxjs";
import {formatNumber} from "@angular/common";
import { InspecaoApiario } from '../inspecaoapiario';
import { IfStmt } from '@angular/compiler';
import { AuthService } from '../login/auth.service';
import { PedidoInstalacao } from '../pedidoinstalacao';

@Component({
    selector: 'app-pedidoinstalacao',
    templateUrl: './pedidoinstalacao.component.html',
    styleUrls: ['./pedidoinstalacao.component.scss']
})
export class PedidoInstalacaoComponent implements OnInit {
    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        if (!this.authService.isAuthenticatedUser()) {
            this.router.navigate(['/funcs/login']);
        }
     }


    private url = "http://localhost:8080/api/";
    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    //Flags: mostrar itens
    showButton: boolean = false;
   
    apicultorID: string = this.authService.getApicultorId();
    data: Date = new Date();
    IdApiario: string;
    selectedValue: string = 'Selecione um apiario';
    apiarios: string[] = [];
    pedidosInstalacao: PedidoInstalacao[]; 
    isSuccess: boolean = false;

    //Lista de apiários e de pedidos
    fetchFirstDropdownValues() {
        this.http.get<Apiario[]>(this.url + 'apiario/getApiariosNotInstalledByApicultorId/' + this.apicultorID).subscribe(
            (data) => {
                this.loop(data)
            },
            (error) => {
                console.error(`Error fetching first dropdown values:`, error);
            }
        );

        //Lista de pedidos
        this.http.get<PedidoInstalacao[]>(this.url +'pedidoinstalacao/getPedidosInstalacao/' + this.apicultorID).subscribe(
            (data) => {
                this.pedidosInstalacao=data;
            },
            (error) => {
                console.error(`Error fetching first dropdown values:`, error);
            });
    }

    loop(apiarios: Apiario[]) {
        for (var val of apiarios) {
            this.apiarios.push(val.id.toString());
        }
    }

    //#region ----ESCOLHER APIÁRIO---
    onValueSelectFirstDropdown(value: string) {
        this.selectedValue = 'Apiário '+value;
        this.IdApiario = value;
        this.showButton = true;
        this.isSuccess = false;
    }
    //#endregion

    //#region  ----SAVE PEDIDO--
    savePedido(){
        
        const apiarioId = parseInt(this.IdApiario, 10);

        console.log(this.IdApiario);
      
        this.http.post(this.url+'pedidoinstalacao', {apiarioId}).subscribe(
            (response) => {
                // Handle the response from the backend as needed
                console.log('Submit data backend response:', response);
                this.showButton=false;
                this.isSuccess=true;

                //atualiza lista de pedidos
                this.http.get<PedidoInstalacao[]>(this.url +'pedidoinstalacao/getPedidosInstalacao/' + this.apicultorID).subscribe(
                    (data) => {
                        this.pedidosInstalacao=data;
                    },
                    (error) => {
                        console.error(`Error fetching first dropdown values:`, error);
                    });
                //this.router.navigate(['/funcs/landing']);
                
            },
            (error) => {
                this.isSuccess=false;
                console.error('Error submitting data to backend:', error);
            }
        );
    }

    //#endregion

    //#region ----OTHERS---
    ngOnInit() {
        this.fetchFirstDropdownValues();
        var rellaxHeader = new Rellax('.rellax-header');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
    }

    ngOnDestroy() {
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
    }
    //#endregion
}
