import { StatusBar } from "expo-status-bar";
import { Alert, StyleSheet, Text, View } from "react-native";
import { Button, Icon, Input } from "@rneui/base";
import { useState } from "react";
import { normalize } from "@rneui/themed";

export default function App() {
  const [name, setName] = useState(''); // Inicializar el estado aquí

  return (
    <View style={styles.container}>
      <Text>RNE</Text>
      <Input
        value={name}
        onChangeText={setName}
        placeholder="Ingrese su nombre"
      />
      <Button
        title="PayPal"
        icon={{
          name: "paypal",
          type: "font-awesome",
          size: 15,
          color: "blue",
        }}
        onPress={()=>{
          Alert.alert("info", "su nombre es:" + name)
        }}
      />
      <StatusBar style="auto" />
      <Text>
        {name}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
    width: "100%",
  },
});
