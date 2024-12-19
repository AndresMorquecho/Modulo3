import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import{GradeForm} from './app/screens/GradeForm';
import{ListGrades} from './app/screens/ListGrades';
import { NavigationContainer } from '@react-navigation/native';
import{createNativeStackNavigator} from '@react-navigation/native-stack'

export default function App() {
  const StackGrandes = createNativeStackNavigator();
  return (



    <NavigationContainer>
      <StackGrandes.Navigator>  
        <StackGrandes.Screen name='ListGradesNav' component={ListGrades} />
        <StackGrandes.Screen name='GradeFormNav' component={GradeForm} />

      </StackGrandes.Navigator>
    </NavigationContainer>
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
