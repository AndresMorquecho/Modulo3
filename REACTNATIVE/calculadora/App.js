import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, TextInput, Button } from "react-native";
import { useState } from "react";
export default function App() {
  const [valor1, setValor1] = useState(0);
  const [valor2, setValor2] = useState(0);
  const [resultado, setResultado] = useState("");

  return (
    <View style={styles.container}>
      <Text>Ejercicio calculadora</Text>
      <StatusBar style="auto" />
      <Text>Valor: 1</Text>
      <TextInput
        style={styles.stilotxt}
        placeholder="Ingrese un valor"
        value={valor1}
        onChangeText={(va1) => {
          setValor1(va1);
        }}
      />
      <Text>Valor: 2</Text>
      <TextInput
        value={valor2}
        style={styles.stilotxt}
        placeholder="Ingrese un valor"
        onChangeText={(val2) => {
          setValor2(val2);
        }}
      />
      <Button
        title="Sumar"
        onPress={() => {
          let res = parseFloat(valor1) + parseFloat(valor2);
          console.log(valor1 + " " + valor2);
          setResultado("Resultado de la suma: " + res);
        }}
      />
      <Button
        title="Restar"
        onPress={() => {
          let res = parseFloat(valor1) - parseFloat(valor2);
          console.log(valor1 + " " + valor2);
          setResultado("Resultado de la resta: " + res);
        }}
      />
      <Button
        title="Multiplicar"
        onPress={() => {
          let res = parseFloat(valor1) * parseFloat(valor2);
          console.log(valor1 + " " + valor2);
          setResultado("Resultado de la multiplicación: " + res);
        }}
      />
      <Button
        title="Dividir"
        onPress={() => {
          let res = parseFloat(valor1) / parseFloat(valor2);
          console.log(valor1 + " " + valor2);
          setResultado("Resultado de la división: " + res);
        }}
      />
      <Button
        title="Limpiar"
        onPress={() => {
          setValor1("");
          setValor2("");
          setResultado("");
        }}
      />

      <Text>{resultado} </Text>
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

  stilotxt: {
    borderColor: "black",
    borderWidth: 1,
    marginTop: 20,
    borderRadius: 8,
  },
});
