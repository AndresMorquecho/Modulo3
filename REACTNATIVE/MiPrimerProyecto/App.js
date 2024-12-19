import { StatusBar } from 'expo-status-bar';
import { Alert, Button,StyleSheet, Text, View } from 'react-native';

export default function App() {


  const despedirse=()=>{

    Alert.alert("Mensaje","Adiosito")

  }


  return (
    <View style={styles.container}>
      <Text>Andres Morquecho</Text>
      <StatusBar style="auto" />
      <Button
      
      title="HOLA"
      
      //Funcion que no recibe parametros y no retorna nada
      onPress={()=>{
        Alert.alert("Hola","Hola desde el botón");
      }}
      />
          
      <Button title='Adiosito'
        onPress={despedirse}

      />


    </View>
    


  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
