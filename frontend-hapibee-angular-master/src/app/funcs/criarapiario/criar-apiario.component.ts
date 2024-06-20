import {Component, OnInit} from '@angular/core';
import * as Rellax from 'rellax';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Colmeia} from "../colmeia";
import {Apiario} from "../apiario";
import {Alca} from "../alca";
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
    selector: 'app-criar-apiario',
    templateUrl: './criar-apiario.component.html',
    styleUrls: ['./criar-apiario.component.scss']
})
export class CriarApiarioComponent implements OnInit {
    data: Date = new Date();
    private url = "http://localhost:8080/api/";

    latitude: string;
    longitude: string;
    freguesia: string;
    apiario: Apiario;
    alcas: Alca[] = [];
    colmeias: Colmeia[] = [];
    apicultorID: string = this.authService.getApicultorId();
    apiarios: Apiario[];
    nColmeias : number;
    nAlcas = 0;
    colmeiasN: { numAlca: number }[] = [];
    zonaControlada = false;
    isSuccess: boolean = false;

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };


    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        if (!this.authService.isAuthenticatedUser()) {
            this.router.navigate(['/funcs/login']);
        }
     }

    updateNumColmeias() {
        this.colmeiasN = Array.from({ length: this.nColmeias }, () => ({ numAlca: 0 }));
    }

    submitApiario(){
        for(let c of this.colmeiasN){
            let col = new Colmeia(null,null);
            for( let i=0; i<c.numAlca; i++){
                this.alcas.push(new Alca(null,null,null));
            }
            col.alcas=this.alcas;
            this.alcas=[];
            this.colmeias.push(col);
        }
        this.apiario={id:0,colmeias:this.colmeias,apicultor_id:Number.parseFloat(this.apicultorID),localizacao:{lat:Number.parseFloat(this.latitude),longi: Number.parseFloat(this.longitude),freg: this.freguesia,zonaProtegida: this.zonaControlada}};
        this.http.post(this.url+'apiario/criar', this.apiario).subscribe(
            (response) => {
                // Handle the response from the backend as needed
                console.log('Submit data backend response:', response);
                this.isSuccess=true;
                this.http.get<Apiario[]>(this.url +'apiario/getApiariosByApicultorId/' + this.apicultorID).subscribe(
                    (data) => {
                        this.apiarios=data;
                    },
                    (error) => {
                        console.error(`Error fetching first dropdown values:`, error);
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
        this.http.get<Apiario[]>(this.url +'apiario/getApiariosByApicultorId/' + this.apicultorID).subscribe(
            (data) => {
                this.apiarios=data;
            },
            (error) => {
                console.error(`Error fetching first dropdown values:`, error);
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
