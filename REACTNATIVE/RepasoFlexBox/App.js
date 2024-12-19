import { StatusBar } from "expo-status-bar";
import { Button, StyleSheet, Text, View } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.cont1}>
        <View style={styles.subcont1}>
          <Button title="x" />
          <Button title="y" />
          <Button title="z" />
        </View>
      </View>

      <View style={styles.cont2}>
        <View style={styles.subcont2_1}>

          <View style={styles.subcont2_1_1}>
            <Button title="Bo2" />
            <Button title="Bo2" />            
          </View>

          <View style={styles.subcont2_1_2}>
          <Button title="Ope1" />
          <Button title="Ope2" />
          <Button title="Ope3" />
          </View>

        </View>



        <View style={styles.subcont2_2}>
            <Button title="Accion 1" />
            <Button title="Accion 2" />   
        </View>


      </View>
      <View style={styles.cont3}>
      <Button title="Boton Final" style={styles.botonconteiner} />  
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },

  cont1: {
    flex: 1,
    backgroundColor: "lightblue",
  },

  cont2: {
    flex: 6,
    backgroundColor: "lightyellow",
    flexDirection: 'column'
  },

  cont3: {
    flex:1,
    backgroundColor: "lightgreen",
    flexDirection: "row",
    justifyContent: "flex-start",
    alignItems: "center",
  },

  botonconteiner:{
    flex:0,
    alignSelf: 'flex-start'
  },  

  subcont1: {
    flex: 1,
    backgroundColor: "lightgreen",
    flexDirection: "row",
    justifyContent: "flex-end",
    alignItems: "center",
  },

  subcont2_1: {
    flex: 4,
    backgroundColor: "lightpink",
    flexDirection: "row",
    justifyContent: "flex-end",
    alignItems: "stretch",
  },

  subcont2_2: {
    flex: 1,
    backgroundColor: "lightblue",
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "flex-end",
  },

  subcont2_1_1: {
    flex: 1,
    backgroundColor: "yellow",
    justifyContent: 'space-around',


  },

  subcont2_1_2: {
    flex: 1,
    backgroundColor: "white",
    justifyContent: 'center',
    alignItems: 'flex-start'

  }
});
