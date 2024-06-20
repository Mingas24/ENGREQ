export class InspecaoApiario{
    constructor(
        public id:number | undefined,
        public apiarioId:number, 
        public dataHora:string,
        public condicoesMetereologicas:string,
        public controlePragasDoencas:string,
        public participarDoenca:boolean,
        public producaoMel:boolean,
        public estadoQuadros:string,
        public comportamentoRainha:string,
        public alimentacao:string,
        public observacoesGerais:string){

        }
}