import { StatusBar } from "expo-status-bar";
import { Button, StyleSheet, Text, TextInput, View } from "react-native";
import { useState } from "react";

export default function App() {
  const [nombre, setNombre] = useState("Ingrese su nombre");
  const[apellido, setApellido] = useState("Ingrese su apellido");
  const[nombreCompleto, setNombreCompleto] = useState("");

  return (
    <View style={styles.container}>
      <Text>Ejemplo text input</Text>


      <TextInput
        style={styles.cajaTexto}
        value={nombre}
        onChangeText={(txt) => {
          setNombre(txt);
      
        }}
      />

      <TextInput

        value={apellido}
        style={styles.cajaTexto}
        onChangeText={(txt)=>{
          setApellido(txt)
        }}

        
      />
      <Text>
        Hola: {nombreCompleto}
      </Text>      

      <Button

        title="Saludar"
        onPress={()=>{
          let completo = nombre + " " + apellido;
          setNombreCompleto(completo);
        }}
      
      />
      <StatusBar style="auto" />
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

  cajaTexto: {
    borderColor: "black",
    borderWidth: 1,
    marginTop: 20,
    borderRadius: 8,
  },
});
