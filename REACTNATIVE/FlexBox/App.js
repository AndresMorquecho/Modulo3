import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.contenedor2}></View>
      <View style={styles.contenedor3}>
        <View style={styles.contenedor4}></View>
        <View style={styles.contenedor5}></View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "gray",
    flexDirection: "column",
  },

  contenedor2: {
    flex: 2,
    backgroundColor: "lightblue",
  },

  contenedor3: {
    flex: 1,
    backgroundColor: "lightgreen",

  },

  contenedor4: {
    flex: 1,
    backgroundColor: "yellow",
  },

  contenedor5: {
    flex: 1,
    backgroundColor: "white",
    flexDirection: "row"
  },
});
