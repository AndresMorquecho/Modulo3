
import { View, Text, StyleSheet } from "react-native"

 export const ContA= ()=>{
    return <View style={styles.container}>
        <Text>soy otra pantalla</Text>
    </View>


}
const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: "4a4e69",
      alignItems: "center",
      justifyContent: "center",
    },
  

  });
  