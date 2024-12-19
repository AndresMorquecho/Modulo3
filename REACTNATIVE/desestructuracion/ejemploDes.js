recupera = () => {
  let frutas = ["pera", "manzana", "sandia"];
  frutas.push("banana");


  return frutas;

};

testRecuperar=()=>{
    let misFrutas = recupera();
    let frutaPrimera = misFrutas[0];
    let otraFruta =  misFrutas[1];


    console.log("Primera fruta >>>: " + frutaPrimera + "    Segunda fruta >>>>>>: " +otraFruta);


}


testRecuperarDes=()=>{
    let [frutaPrimera,frutaSegunda,frutaTercera] = recupera();

        console.log("1 "+ frutaPrimera );
        console.log("2 "+ frutaSegunda );
        console.log("3 "+ frutaTercera );

}   


