// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    private isAuthenticated:boolean = false;
    private apicultorId: string;
    private apiUrl = 'http://localhost:8080/auth/public';

    constructor(private http: HttpClient, private router: Router) {}

    login(username: string, password: string): Observable<any> {
        const credentials = { username, password };
        return this.http.post(`${this.apiUrl}/login`, credentials)
        .pipe(
          tap((response: any) => {
            this.isAuthenticated = true;
            this.apicultorId = response.id;
          })
        );
    }
    
    logout(): void {
        this.isAuthenticated = false;
        this.apicultorId = null;
        // Redirecione para a página de login ao realizar logout
        this.router.navigate(['/funcs/login']);
        this.updateAuthenticationStatus(false);
    }
    
    isAuthenticatedUser(): boolean {
      return this.isAuthenticated;
    }

    getApicultorId(): string {
      return this.apicultorId;
    }

    // Observable no serviço de autenticação
    private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

    // Método para obter um observable do estado de autenticação
    isAuthenticated$(): Observable<boolean> {
        return this.isAuthenticatedSubject.asObservable();
    }

    // Método para atualizar o estado de autenticação
    updateAuthenticationStatus(isAuthenticated: boolean) {
      this.isAuthenticated = isAuthenticated;
      this.isAuthenticatedSubject.next(isAuthenticated);
    }

}
