export class Accion{
    
    constructor( public idAccion:number,
        public team:String ,
        public typeAccion:String ,
        public subtype:String ,
        public periodAccion:number ,
        public startFrame:number ,
        public startTime:number ,
        public endFrame:number ,
        public endTime:number ,
        public fromP:String ,
        public toP:String ,
        public fecha:Date ,
        public startX:number ,
        public startY:number ,
        public endX:number ,
        public endY:number ){}
    
    
}