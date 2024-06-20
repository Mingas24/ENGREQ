import {Component, OnInit} from '@angular/core';
import * as Rellax from 'rellax';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Apiario} from "../apiario";
import { Router } from '@angular/router';
import {catchError, Observable, of} from "rxjs";
import {formatNumber} from "@angular/common";
import { InspecaoApiario } from '../inspecaoapiario';
import { IfStmt } from '@angular/compiler';
import {Pedidotrans} from "../pedidotrans";
import {AuthService} from "../login/auth.service";
import {DeclaracaoAnual} from "../declaracaoanual";

@Component({
    selector: 'app-pedidotrans',
    templateUrl: './pedido-trans.component.html',
    styleUrls: ['./pedido-trans.component.scss']
})
export class PedidoTransComponent implements OnInit {
    private url = "http://localhost:8080/api/";
    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    //Flags: mostrar itens
    showButtons: boolean = false;
    showListInspecoes: boolean = false;
    showNewInspecao: boolean = false;
    showDetailsInspecao: boolean = false;
    showEditInspecao: boolean = false;

    data: Date = new Date();
    apiId : string = this.authService.getApicultorId();
    selectedValue: string = 'Selecione um apiario';
    apiarios: string[] = [];
    inspecoes: InspecaoApiario[];
    inspecao: InspecaoApiario | undefined;
    novaInspecao: InspecaoApiario;
    latitude: number;
    longitude: number;
    freguesia: string;
    showNewBox: boolean = false;
    isUpdate: boolean | undefined;
    pedidoTrans : Pedidotrans;
    isSuccess: boolean = false;
    pedidos: Pedidotrans[];

    fetchFirstDropdownValues() {
        this.http.get<Apiario[]>(this.url + 'apiario/getApiariosByApicultorId/' + this.apiId).subscribe(
            (data) => {
                this.loop(data)
            },
            (error) => {
                console.error(`Error fetching first dropdown values:`, error);
            }
        );
    }

    loop(apiarios: Apiario[]) {
        for (var val of apiarios) {
            this.apiarios.push(val.id.toString());
        }
    }


    onValueSelectFirstDropdown(value: string) {
        this.selectedValue = 'Apiário '+value;
        this.showNewBox = true;
        this.apiId = value;
    }

    submitPedidoTrans(){
        this.pedidoTrans={
            localizacaoDTO:{
                lat: this.latitude,
                longi: this.longitude,
                freg: this.freguesia,
                zonaProtegida : false
            },
            apiarioId:Number.parseFloat(this.apiId),
            aprovado:'ND'
        }
        this.http.post(this.url+'pedidotrans/criar', this.pedidoTrans).subscribe(
            (response) => {
                this.isSuccess = true;
                // Handle the response from the backend as needed
                console.log('Submit data backend response:', response);
                this.http.get(this.url + 'pedidotrans/get/' + this.apiId, {}).subscribe(
                    (response: Pedidotrans[]) => {
                        this.pedidos = response;
                    },
                    (error) => {
                        console.error('Error fetching inspection list:', error);
                    }
                );

                // Additional actions can be added here if needed
            },
            (error) => {
                console.error('Error submitting data to backend:', error);
            }
        );

    }

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        if (!this.authService.isAuthenticatedUser()) {
            this.router.navigate(['/funcs/login']);
        }
    }

    //#region ----OTHERS---
    ngOnInit() {
        this.fetchFirstDropdownValues();
        var rellaxHeader = new Rellax('.rellax-header');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
        this.http.get(this.url + 'pedidotrans/get/' + this.apiId, {}).subscribe(
            (response: Pedidotrans[]) => {
                this.pedidos = response;
            },
            (error) => {
                console.error('Error fetching inspection list:', error);
            }
        );



    }

    ngOnDestroy() {
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
    }
    //#endregion
}
