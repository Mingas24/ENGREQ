export class PedidoInstalacao{
    constructor(
        public id:number | undefined,
        public apiarioId:number, 
        public autorizado:boolean,
        public observacoes:string){

        }
}