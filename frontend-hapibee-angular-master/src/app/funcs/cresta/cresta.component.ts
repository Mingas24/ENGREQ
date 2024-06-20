import {Component, OnInit} from '@angular/core';
import * as Rellax from 'rellax';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Colmeia} from "../colmeia";
import {Apiario} from "../apiario";
import {Alca} from "../alca";
import {Cresta} from "../cresta";
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import {InspecaoApiario} from "../inspecaoapiario";

@Component({
    selector: 'app-cresta',
    templateUrl: './cresta.component.html',
    styleUrls: ['./cresta.component.scss']
})
export class CrestaComponent implements OnInit {
    data: Date = new Date();
    selectedValue: string = 'Selecione um apiario';
    selectedValue2: string = 'Selecione uma colmeia';
    values: string[] = [];
    valuesC: string[] = [];
    private url = "http://localhost:8080/api/";
    showNewDropdown: boolean = false;
    showBoxes: boolean = false;
    colmeias: Colmeia[];
    colmeiaId: string;
    cresta: Cresta
    crestas: Cresta[];
    alcas: Alca[];
    isSuccess: boolean = false;
    apicultorID: string = this.authService.getApicultorId();

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        if (!this.authService.isAuthenticatedUser()) {
            this.router.navigate(['/funcs/login']);
        }
     }

    fetchFirstDropdownValues() {
        this.http.get<Apiario[]>(this.url +'apiario/getApiariosByApicultorId/' + this.apicultorID).subscribe(
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
            this.values.push(val.id.toString());
        }
    }

    loop2(colmeias: Colmeia[]) {
        for (var val of colmeias) {
            this.valuesC.push(val.id.toString());
        }
    }

    onValueSelectFirstDropdown(value: string) {
        // Send data to the backend when an option is selected from the first dropdown
        this.http.post(this.url + 'colmeia/getByApiario', value).subscribe(
            (response: Colmeia[]) => {
                // Handle the response from the backend as needed
                console.log('First dropdown backend response:', response);
                this.selectedValue = 'Apiario '+value;
                // Show the second dropdown and fetch its values based on the response
                this.loop2(response);
                this.showNewDropdown = true;
                this.colmeias = response;
            },
            (error) => {
                console.error('Error sending data to backend for the first dropdown:', error);
            }
        );
    }

    onValueSelectSecondDropdown(value: string) {
        this.selectedValue2 = 'Colmeia '+value;
        this.http.post(this.url + 'cresta/alcas', value).subscribe(
            (response: Alca[]) => {
                // Handle the response from the backend as needed
                console.log('First dropdown backend response:', response);
                // Show the second dropdown and fetch its values based on the response
                this.alcas=response;
                this.colmeiaId= value;
                this.showBoxes=true;
            },
            (error) => {
                console.error('Error sending data to backend for the first dropdown:', error);
            }
        );
    }
    submitCresta(){
        this.cresta= {id: 0, colmeia:{id: Number(this.colmeiaId), alcas: this.alcas}, data:null}
        this.http.post(this.url+'cresta/realizar', this.cresta).subscribe(
            (response) => {
                // Handle the response from the backend as needed
                console.log('Submit data backend response:', response);
                this.isSuccess=true;
                this.http.get(this.url + 'cresta/'+this.apicultorID, {}).subscribe(
                    (response: Cresta[]) => {
                        this.crestas = response;
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
        this.fetchFirstDropdownValues();
        var rellaxHeader = new Rellax('.rellax-header');

        /*var body = document.getElementsByTagName('body')[0];
        body.classList.add('landing-page');*/
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
        this.http.get(this.url + 'cresta/'+this.apicultorID, {}).subscribe(
            (response: Cresta[]) => {
                this.crestas = response;
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
