import {Localizacao} from "./localizacao";

export class Pedidotrans{
    constructor(public localizacaoDTO: Localizacao, public apiarioId: number, public aprovado:string ) {

    }


}