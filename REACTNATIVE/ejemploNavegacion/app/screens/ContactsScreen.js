
import { Button, StyleSheet, Text, View } from 'react-native';


 export const Contacts =({navigation})=>{

    return <View style={styles.container} >
        <Text>Estoy en contacts</Text>
        <Button title='IR A HOME' onPress={()=>{
           
           navigation.navigate('HomeNav')

        }} />

    </View>
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'column',
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        paddingInline: 1

    },
  });