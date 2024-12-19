import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, TextInput, Button, Alert } from "react-native";
import { useState } from "react";

export default function App() {
  const [valor, setValor] = useState(0);
  const [resultado, setResultado] = useState(0);
  return (
    <View style={styles.container}>
      <Text>Conversor de divisas</Text>
      <StatusBar style="auto" />

      <Text>Ingrese el valor ha convertir en dolares</Text>
      <TextInput
        style={styles.styletxt}
        placeholder="Ingrese un valor"
        onChangeText={(valorConvertir) => {
          setValor(valorConvertir);
        }}
      />
      <Text>Conversores</Text>

      <Button
        title="Pesos Mexicanos"
        onPress={() => {
          let valorPesoMX = 20.12;
          let valorConvertido = valorPesoMX * parseFloat(valor);
          setResultado(valorConvertido);
          Alert.alert("valor en pesos Mexicanos: " + valorConvertido.toFixed(2))
        }}
      />

      <Button title="Pesos Colombianos"
      onPress={() => {
        let valorPesoCO = 4390.73 ;
        let valorConvertido = valorPesoCO * parseFloat(valor);
        setResultado(valorConvertido);
        Alert.alert("valor en pesos Colombianos: " + valorConvertido.toFixed(2))
      }} />

      <Button title="Euros"onPress={() => {
          let valorPesoMX = 1.06;
          let valorConvertido = valorPesoMX * parseFloat(valor);
          setResultado(valorConvertido);
          Alert.alert("valor en Euros: " + valorConvertido.toFixed(2))
        }} />
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

  styletxt: {
    borderColor: "black",
    borderWidth: 1,
    marginTop: 20,
    borderRadius: 8,
    fontSize: 15,
  },
});
