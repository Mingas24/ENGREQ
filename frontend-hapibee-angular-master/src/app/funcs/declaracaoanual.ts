export class DeclaracaoAnual{
    constructor(public apicultorNIF: String, public apicultorCodigoResidencia: number,
                public apicultorCodigoFreguesia: number, public apicultorCulturaIntensiva:boolean,
                public transumancia: boolean, public registoInicioAtividade:String,
                public fechoAtividade: String, public pedidoAlteracao: String,
                public zonaControlada: boolean, public aprovacao: String){
    }
}