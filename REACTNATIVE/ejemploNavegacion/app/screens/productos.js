
import { Button, StyleSheet, Text, View } from 'react-native';

export const  Productos=({navigation})=>{
    return <View>

        <Text>Estoy en Productos</Text>
        <Button title='IR A HOME' onPress={()=>{
            navigation.navigate('HomeNav')
        }} />
    </View>


}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        justifyContent: 'center',
        alignItems: 'center',
        paddingInline: 1,
        backgroundColor: 'red'
    },
  });