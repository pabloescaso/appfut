export class Partido{
    constructor(
    public idPartido:number,
    public nombre:String,
    public homeClub:String,
    public awayClub:String,
    public descripcion:String,
    public resultado:String,
    public fecha:Date){}
}