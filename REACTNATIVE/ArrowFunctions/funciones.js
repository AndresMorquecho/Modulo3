ejecutarSumar = () => {
  
  /*
  let valor1 = recuperarEntero("txtvalor1");
  let valor2 = recuperarEntero("txtvalor2");
  let resutaldosuma;
  console.log("valor 1: " + valor1 + "  valor 2: " + valor2);
  resutaldosuma = sumar(valor1, valor2);
  console.log("Resultado de la suma: " + resutaldosuma);
  */


  ejecutarOperacion(sumar);


  };

sumar = (sum1, sum2) => {
  let resultado;
  resultado = sum1 + sum2;
  return resultado;
};




ejecutarOperacion=(operar)=>{
  let valor1 = recuperarFloat("txtvalor1");
  let valor2 = recuperarFloat("txtvalor2");
  let resultado;

  resultado = operar(valor1,valor2);
  
  console.log(resultado);

  
};

resta = (val1, val2) => {
  let resultado = val1 - val2;
  return resultado;
};

ejecutarResta = () => {
  let valor1 = recuperarFloat("txtvalor1");
  let valor2 = recuperarFloat("txtvalor2");
  let resultadoResta = resta(valor1, valor2);
  console.log(
    "El resultado de restar: " +
      valor1 +
      " y el valor: " +
      valor2 +
      " da como resultado: " +
      resultadoResta
  );


};






ejecutar = (fn) => {
  console.log("estoy ejecutando funciones");
  fn();

};


saludar=()=>{
  alert("aprendiendo programacion funcional");
}

imprimir=()=>{
  console.log("estoy imprimiendo");
}

testEjecutar=()=>{
  ejecutar(imprimir);
  ejecutar(saludar);
  ejecutar(
    ()=>{
      alert("funcion sin nombreo anonima");
    }  
  );

}