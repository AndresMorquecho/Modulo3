import { StatusBar } from "expo-status-bar";
import { Alert, Button, StyleSheet, Text, View } from "react-native";

export default function App() {
  const Finalizar = () => {
    Alert.alert("Sesion", "Su sesion ha finalizado con exito");
  };
  return (
    <View style={styles.container}>
      <Text>App</Text>
      <StatusBar style="auto" />

      <Button title="Finalizar" onPress={Finalizar} />
      <Button
        title="Inciar"
        onPress={() => {
          Alert.alert("Sesion", "Su sesion ha iniciado con exito");
        }}
      />
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
