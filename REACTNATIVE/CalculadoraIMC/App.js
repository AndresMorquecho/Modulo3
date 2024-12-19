import { StatusBar } from "expo-status-bar";
import { Alert, Button, StyleSheet, Text, TextInput, View } from "react-native";
import { useState } from "react";
export default function App() {
  const [estatura, setEstatura] = useState();
  const [peso, setPeso] = useState();
  const[resultado, setresultado] = useState();
  return (
    <View style={styles.container}>
      <View style={styles.contTitulo}>
        <Text style={styles.titulo}>Calculadora de IMC</Text>
      </View>

      <View>
        <Text>Ingrese su estatura</Text>

        <TextInput placeholder="en centimetros" style={styles.caja}
          onChangeText={(txtestatura)=>{
            setEstatura(txtestatura);
          }}
        
        ></TextInput>
        <Text>Ingrese su peso</Text>
        <TextInput placeholder="en kilogramos" style={styles.caja} 
          onChangeText={(txtpeso)=>{
            setPeso(txtpeso)
          }}
        >

        </TextInput>
      </View>

      <View>
        <Button title="Calcular"
          onPress={()=>{
              let esturaMetros = parseFloat(estatura) / 100;
              let imc = peso / (esturaMetros ** 2)
              let calculoimc = imc.toFixed(2)
              let resultadoimctxt = "";
              if(calculoimc < 18.5){
                resultadoimctxt = "Su imc es de: " + calculoimc + " Peso inferior al normal";
              }else if(calculoimc > 18.5 && calculoimc < 24.9){
                resultadoimctxt = "Su imc es de: " + calculoimc + " Normal";
              }else if(calculoimc > 24.9 && calculoimc < 29.9){
                resultadoimctxt = "Su imc es de: " + calculoimc + " Peso superior al normal";
              }else if(calculoimc > 30.0){
                resultadoimctxt = "Su imc es de: " + calculoimc + " Obseidad";
              }

              setresultado(resultadoimctxt)

          }}  
        ></Button>
      </View>

      <View>
        <Text>{resultado}</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },

  caja: {
    borderColor: "black",
    borderWidth: 1,
    borderRadius: 5,
  },

  titulo: {
    fontSize: 20,
    fontWeight: "bold",
  },
  contTitulo: {
    alignContent: "flex-start",
  },
});
