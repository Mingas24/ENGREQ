import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NouisliderModule } from 'ng2-nouislider';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { JwBootstrapSwitchNg2Module } from 'jw-bootstrap-switch-ng2';
import { AgmCoreModule } from '@agm/core';

import { LandingComponent } from './landing/landing.component';
import { FuncsComponent } from './funcs.component';
import {CrestaComponent} from "./cresta/cresta.component";
import {LoginComponent} from "./login/login.component";
import {CriarApiarioComponent} from "./criarapiario/criar-apiario.component";
import { InspecaoComponent } from './inspecaoapiario/inspecao.component';
import { DeclaracaoAnualComponent } from './declaracaoanual/declaracao-anual.component';
import { PedidoTransComponent } from './pedidotrans/pedido-trans.component';
import { PedidoInstalacaoComponent } from './pedidoinstalacao/pedidoinstalacao.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        NgbModule,
        NouisliderModule,
        JwBootstrapSwitchNg2Module,
        AgmCoreModule.forRoot({
            apiKey: 'YOUR_KEY_HERE'
        })
    ],
    declarations: [
        LandingComponent,
        FuncsComponent,
        CrestaComponent,
        LoginComponent,
        CriarApiarioComponent,
        InspecaoComponent,
        PedidoInstalacaoComponent,
        DeclaracaoAnualComponent,
    PedidoTransComponent],
    providers:[
        DatePipe
    ]
})
export class FuncsModule { }
