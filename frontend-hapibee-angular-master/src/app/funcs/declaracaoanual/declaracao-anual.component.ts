import {Component, OnDestroy, OnInit} from '@angular/core';
import * as Rellax from 'rellax';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Colmeia} from "../colmeia";
import {Apiario} from "../apiario";
import {Alca} from "../alca";
import {Cresta} from "../cresta";
import {DeclaracaoAnual} from "../declaracaoanual";
import {Router} from "@angular/router";
import {AuthService} from "../login/auth.service";

@Component({
    selector: 'app-declaracao-anual',
    templateUrl: './declaracao-anual.component.html',
    styleUrls: ['./declaracao-anual.component.scss']
})
export class DeclaracaoAnualComponent implements OnInit, OnDestroy{
    data: Date = new Date();
    values: string[] = [];
    private url = "http://localhost:8080/api/";
    showBoxes: boolean = false;
    declaracaoAnual: DeclaracaoAnual;
    apicultorNIF: String;
    apicultorCodigoResidencia: number;
    apicultorCodigoFreguesia: number;
    apicultorCulturaIntensiva: boolean;
    transumancia: boolean;
    registoInicioAtividade: String;
    fechoAtividade: String;
    pedidoAlteracao: String;
    zonaControlada: boolean;
    aprovacao:String;
    isSuccess:boolean = false;

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        if (!this.authService.isAuthenticatedUser()) {
            this.router.navigate(['/funcs/login']);
        }
    }
    apicultorID: string = this.authService.getApicultorId();
    declaracoes: DeclaracaoAnual[];
    submitDeclaracaoAnual(){
        this.declaracaoAnual= {apicultorNIF: this.apicultorNIF, apicultorCodigoResidencia: this.apicultorCodigoResidencia,
            apicultorCodigoFreguesia: this.apicultorCodigoFreguesia, apicultorCulturaIntensiva: this.apicultorCulturaIntensiva,
            transumancia:this.transumancia, registoInicioAtividade: this.registoInicioAtividade,fechoAtividade: this.fechoAtividade,
            pedidoAlteracao: this.pedidoAlteracao, zonaControlada: this.zonaControlada, aprovacao: this.aprovacao}
        this.http.post(this.url+'declaracaoAnual/declarar', this.declaracaoAnual).subscribe(
            (response) => {
                this.isSuccess = true;
                // Handle the response from the backend as needed
                console.log('Submit data backend response:', response);
                this.http.get(this.url + 'declaracaoAnual/get/' + this.apicultorID, {}).subscribe(
                    (response: DeclaracaoAnual[]) => {
                        this.declaracoes = response;
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
    ngOnInit() {
        var rellaxHeader = new Rellax('.rellax-header');

        /*var body = document.getElementsByTagName('body')[0];
        body.classList.add('landing-page');*/
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
        this.http.get(this.url + 'declaracaoAnual/get/' + this.apicultorID, {}).subscribe(
            (response: DeclaracaoAnual[]) => {
                this.declaracoes = response;
            },
            (error) => {
                console.error('Error fetching inspection list:', error);
            }
        );
    }
    ngOnDestroy() {
        /*var body = document.getElementsByTagName('body')[0];
        body.classList.remove('landing-page');*/
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
    }
}
