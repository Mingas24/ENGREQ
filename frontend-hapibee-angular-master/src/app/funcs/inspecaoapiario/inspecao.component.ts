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

@Component({
    selector: 'app-inspecao',
    templateUrl: './inspecao.component.html',
    styleUrls: ['./inspecao.component.scss']
})
export class InspecaoComponent implements OnInit {
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
    showButtons: boolean = false;
    showListInspecoes: boolean = false;
    showNewInspecao: boolean = false;
    showDetailsInspecao: boolean = false;
    showEditInspecao: boolean = false;
    
    apicultorID: string = this.authService.getApicultorId();
    data: Date = new Date();
    apiarioId : string;
    selectedValue: string = 'Selecione um apiario';
    apiarios: string[] = [];
    inspecoes: InspecaoApiario[];
    inspecao: InspecaoApiario | undefined;
    novaInspecao: InspecaoApiario;
    isUpdate: boolean | undefined;

    //Lista de apiários
    fetchFirstDropdownValues() {
        this.http.get<Apiario[]>(this.url + 'apiario/getApiariosByApicultorId/' + this.apicultorID).subscribe(
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

    //#region ----ESCOLHER APIÁRIO---
    onValueSelectFirstDropdown(value: string) {
        this.selectedValue = 'Apiário '+value;
        this.showButtons = true;
        this.apiarioId = value;
        this.showListInspecoes = false;
        this.showNewInspecao = false;
        this.showDetailsInspecao = false;
    }
    //#endregion

    //#region  ----LISTA DE INSPEÇÕES DE UM APIÁRIO---
    listaInspecoes() {
        this.showListInspecoes = true;
        this.showButtons = false;
        this.showNewInspecao = false;
        this.showDetailsInspecao = false;
        this.showEditInspecao = false;
        ///getInspecoesByApiarioId/{apiarioId}
        this.http.get(this.url + 'inspecaoapiario/getInspecoesByApiarioId/' + this.apiarioId, {}).subscribe(
            (response: InspecaoApiario[]) => {
                this.inspecoes = response;
            },
            (error) => {
                console.error('Error fetching inspection list:', error);
            }
        );
    }
    //#endregion

    //#region  ----DETALHES INSPEÇÃO---
    detalhesInspecao(id:string){
        this.showDetailsInspecao = true;
        this.showListInspecoes = false;
        this.showButtons = false;
        this.showNewInspecao = false;
        this.showEditInspecao = false;
        ///getInspecaoDetailsById/{id}
        this.http.get(this.url + 'inspecaoapiario/getInspecaoDetailsById/' + id, {}).subscribe(
            (response: InspecaoApiario) => {
                this.inspecao = response;
            },
            (error) => {
                console.error('Error receiving data', error);
            }
        );
    }
    //#endregion

    //#region ----EDITAR INSPEÇÃO----
    editarInspecao(id:string){
        this.showEditInspecao = true;
        this.showDetailsInspecao = false;
        this.showListInspecoes = false;
        this.showButtons = false;
        this.showNewInspecao = false;
        //Coloca flag a true
        this.isUpdate = true;
        ///getInspecaoDetailsById/{id}
        this.http.get(this.url + 'inspecaoapiario/getInspecaoDetailsById/' + id, {}).subscribe(
            (response: InspecaoApiario) => {
                this.inspecao = response;
            },
            (error) => {
                console.error('Error receiving data', error);
            }
        );
    }
    //#endregion

    //#region  ----NOVA INSPEÇÃO----
    newInspecao(){
        this.showNewInspecao = true;
        this.showListInspecoes = false;
        this.showEditInspecao = false;
        this.showDetailsInspecao = false;
        this.showButtons = false;
        //flag a false
        this.isUpdate = false;
        this.novaInspecao = new InspecaoApiario(null, parseInt(this.apiarioId,10),"","","",false,false,"","","","") ;
    }
    //#endregion

    //#region ----APAGA INSPEÇÃO----
    apagaInspecao(id:string){
        this.showListInspecoes = true;
        this.showEditInspecao = false;
        this.showDetailsInspecao = false;
        this.showButtons = false;
        this.showNewInspecao = false;

        //{id}
        this.http.delete(this.url + 'inspecaoapiario/' + id, {}).subscribe(
            () => {
                console.log('Inspeção excluída com sucesso.');
                this.listaInspecoes();
            },
            (error) => {
                this.listaInspecoes();
                console.error('Erro a excluir inspeção.', error);
            }
        );
    }

    //#endregion

    //#region  ----SAVE INSPEÇÃO : POST | PUT
    saveInspecao(id:string){
        this.showListInspecoes = false;
        this.showEditInspecao = false;
        this.showDetailsInspecao = false;
        this.showButtons = false;
        this.showNewInspecao = false;
        //Update
        if(this.isUpdate == true){
            this.http.put(this.url+'inspecaoapiario/' + id, this.inspecao).subscribe(
                (response) => {
                    // Handle the response from the backend as needed
                    console.log('Submit data backend response:', response);
    
                    this.showListInspecoes = true;
                },
                (error) => {
                    this.showEditInspecao = true;
                    console.error('Error submitting data to backend:', error);
                }
            );
        }else{ //Post
            console.log(this.novaInspecao.alimentacao);
            this.novaInspecao.apiarioId=parseInt(this.apiarioId,10);
            this.http.post(this.url+'inspecaoapiario', this.novaInspecao).subscribe(
                (response) => {
                    // Handle the response from the backend as needed
                    console.log('Submit data backend response:', response);
    
                    this.showListInspecoes = true;
                    this.listaInspecoes();
                },
                (error) => {
                    this.showNewInspecao = true;
                    console.error('Error submitting data to backend:', error);
                }
            );
        }

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
