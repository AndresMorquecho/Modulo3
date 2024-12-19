import { StatusBar } from "expo-status-bar";
import { Alert, Button, StyleSheet, Text, View } from "react-native";
import { useState } from "react";
export default function App() {
  const [vidas, setVidas] = useState(5);

  const PerderVida = () => {
    if (vidas > 0) {
      setVidas(vidas - 1);
      if (vidas === 0) {
        Alert.alert("Advertencia", "Game Over");
      }
    } else {
      Alert.alert("Advertencia", "Game Over");
    }
  };

  const Premiar = () => {
    setVidas(vidas + 3);
  };

  return (
    <View style={styles.container}>
      <Text>Juego</Text>
      <Text>Vidas: {vidas}</Text>

      <Button title="Perder vida" onPress={PerderVida} />
      <Button title="Premiar" onPress={Premiar} />
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
});
