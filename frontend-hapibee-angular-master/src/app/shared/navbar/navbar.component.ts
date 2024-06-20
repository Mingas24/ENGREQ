import { Component, OnInit, ElementRef, ChangeDetectorRef } from '@angular/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AuthService } from 'app/funcs/login/auth.service';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
    private toggleButton: any;
    private sidebarVisible: boolean;
    data : Date = new Date();
    isAuthenticated: boolean = false;


    constructor(public location: Location, private element : ElementRef, private authService: AuthService,private cdRef: ChangeDetectorRef) {
        this.sidebarVisible = false;
        this.isAuthenticated = this.authService.isAuthenticatedUser();
    }


    ngOnInit() {
        const navbar: HTMLElement = this.element.nativeElement;
        this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
    }

    //Verificar alterações do estado
    ngDoCheck() {
        const isAuthenticated = this.authService.isAuthenticatedUser();
        if (this.isAuthenticated !== isAuthenticated) {
            this.isAuthenticated = isAuthenticated;
            this.cdRef.detectChanges();
        }
       // console.log(isAuthenticated);
    }

    sidebarOpen() {
        const toggleButton = this.toggleButton;
        const html = document.getElementsByTagName('html')[0];
        setTimeout(function(){
            toggleButton.classList.add('toggled');
        }, 500);
        html.classList.add('nav-open');

        this.sidebarVisible = true;
    };
    sidebarClose() {
        const html = document.getElementsByTagName('html')[0];
        // console.log(html);
        this.toggleButton.classList.remove('toggled');
        this.sidebarVisible = false;
        html.classList.remove('nav-open');
    };
    sidebarToggle() {
        // const toggleButton = this.toggleButton;
        // const body = document.getElementsByTagName('body')[0];
        if (this.sidebarVisible === false) {
            this.sidebarOpen();
        } else {
            this.sidebarClose();
        }
    };
  
    isDocumentation() {
        var titlee = this.location.prepareExternalUrl(this.location.path());
        if( titlee === '/documentation' ) {
            return true;
        }
        else {
            return false;
        }
    }
    logout() {
        // Lógica de logout
        this.authService.logout();
    }
}
