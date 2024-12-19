import { Button, StyleSheet, Text, View } from 'react-native';



export const Home=({navigation})=>{
    return <View style={styles.container}>
        <View>
          <Text>HOME</Text>
        </View>

        <View style={styles.contendorBotones}>
          <Button title='Ir a contactos' onPress={()=>{
                  navigation.navigate('ContactsNav');
          }} />

          <Button title= 'ir a productos' onPress={()=>{
                navigation.navigate('ProductosNav')


          }}  />
        </View>
    </View> 
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      justifyContent: 'center',
      alignItems: 'center',
      paddingInline: 1
    },

    contendorBotones:{
      flexDirection: 'row',
      justifyContent: 'space-around',
      paddingInline: 1,
      width: '100%' 
    }
  });
  