import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    data : Date = new Date();
    focus;
    focus1;
    username: string;
    password: string;
    apicultorId: string |undefined;
    
    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit() {
        var body = document.getElementsByTagName('body')[0];
        body.classList.add('login-page');
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
    }
    ngOnDestroy(){
        var body = document.getElementsByTagName('body')[0];
        body.classList.remove('login-page');
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
    }

    //Login
    onSubmit() {
        console.log(this.username);
        this.authService.login(this.username, this.password).subscribe(
            (response) => {
                this.apicultorId = this.authService.getApicultorId();
            console.log(this.apicultorId);
            this.router.navigate(['/funcs/landing']);
            },
            (error) => {
                this.router.navigate(['/funcs/login']);
            }
        );
    }
}
