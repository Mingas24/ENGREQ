import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { ComponentsComponent } from './components/components.component';
import { LandingComponent } from './funcs/landing/landing.component';
import { NucleoiconsComponent } from './components/nucleoicons/nucleoicons.component';
import {CrestaComponent} from "./funcs/cresta/cresta.component";
import {LoginComponent} from "./funcs/login/login.component";
import {CriarApiarioComponent} from "./funcs/criarapiario/criar-apiario.component";
import { InspecaoComponent } from './funcs/inspecaoapiario/inspecao.component';
import { DeclaracaoAnualComponent } from './funcs/declaracaoanual/declaracao-anual.component';
import {PedidoTransComponent} from "./funcs/pedidotrans/pedido-trans.component";
import { PedidoInstalacaoComponent } from './funcs/pedidoinstalacao/pedidoinstalacao.component';

const routes: Routes =[
    { path: '', redirectTo: 'funcs/login', pathMatch: 'full' },
    { path: 'funcs/landing',     component: LandingComponent },
    { path: 'funcs/cresta',     component: CrestaComponent},
    { path: 'funcs/login',     component: LoginComponent},
    { path: 'funcs/criarapiario',     component: CriarApiarioComponent},
    {path: 'funcs/inspecaoapiario', component: InspecaoComponent},
    { path: 'funcs/declaracaoanual', component: DeclaracaoAnualComponent },
    {path: 'funcs/pedidotrans', component: PedidoTransComponent},
    {path: 'funcs/pedidoinstalacao', component: PedidoInstalacaoComponent}
];

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        RouterModule.forRoot(routes)
    ],
    exports: [
    ],
})
export class AppRoutingModule { }
