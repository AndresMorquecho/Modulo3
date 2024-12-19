import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import React, { useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ListaProductos} from './app/servicesProductos';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs'
import { ContA } from './app/contA';
import { ContB } from './app/contB';
import Icon from 'react-native-vector-icons/Ionicons';

const Drawer = createDrawerNavigator();
const Tab = createBottomTabNavigator();

const TabNav = ()=>{

return(
  <Tab.Navigator>
    <Tab.Screen

        name='Contenido A'
        component={ContA}    
        options={{
          headerShown: false,
          tabBarLabel: "Configuracion",
          tabBarIcon: ()=>{
            return <Ionicons name="home" color="black" size={20} />
          }

        }}
    />

      <Tab.Screen

      name='Contenido B'
      component={ContB}    
      options={{
        headerShown: false,
        tabBarLabel: "Acerca De",
        tabBarIcon: ()=>{
          return <Ionicons name="settings" color="black" size={20} />
        }
      }}
      />    

    
  </Tab.Navigator>
)

}



export default function App() {
  return (
  <NavigationContainer>

    <Drawer.Navigator>
      <Drawer.Screen
        name='Productos'
        component={ListaProductos}
      />

      <Drawer.Screen
        name='Proveedores'
        component={TabNav}
    
      />

      <Drawer.Screen
        name='Ventas'
        component={ContB}
      />            

    </Drawer.Navigator>
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

  
  contenedorLista: {
    //backgroundColor: 'red',
    flex: 1,
    flexDirection: "row",
    alignItems: "stretch",
  },
});
