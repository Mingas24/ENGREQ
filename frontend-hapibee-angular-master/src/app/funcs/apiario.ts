import {Colmeia} from "./colmeia";
import {Localizacao} from "./localizacao";
export class Apiario{
    constructor(public id: number, public colmeias: Colmeia[], public apicultor_id: number, public localizacao: Localizacao) {
    }
}
