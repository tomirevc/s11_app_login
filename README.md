# S11: LOGIN BÁSICO CON LIVE DATA Y VIEW MODEL

Esta aplicación Android demuestra un sistema de inicio de sesión y registro de usuarios usando Firebase Authentication y Firestore, aplicando la arquitectura MVVM, LiveData y ViewModel para una mejor separación de responsabilidades y reactividad de la UI.  

![Image](https://github.com/user-attachments/assets/76d0facd-8493-45db-bc83-b42ea9fe34a8)  

---

## Inicio de sesión

Permite a los usuarios autenticarse usando su correo electrónico y contraseña. El estado del usuario se observa mediante LiveData y se maneja con un ViewModel para separar la lógica de la vista. Si el usuario ya está registrado, accede directamente a la pantalla principal.  
![Image](https://github.com/user-attachments/assets/ecda0f2e-315d-450c-ae62-5d7813ca74e1)  

![Image](https://github.com/user-attachments/assets/559fc264-3c86-46f3-a171-8928f2095f4a) 

---

## Registro

Ofrece un formulario donde el usuario puede ingresar su nombre, correo electrónico y contraseña. El formulario valida los datos antes de permitir el registro. Al registrarse, el usuario se agrega a la base de datos de Firebase Firestore y puede iniciar sesión de inmediato.  
![Image](https://github.com/user-attachments/assets/150634a4-c146-4f1f-92b2-9f228691c682)  

![Image](https://github.com/user-attachments/assets/5ca99eee-2921-4894-9f03-48fc2e825ee8)  

---

## Actividad principal

Tras iniciar sesión correctamente, el usuario es dirigido a la actividad principal, donde se muestra su nombre y un mensaje de bienvenida. Esta pantalla también ofrece la opción de cerrar sesión. El acceso a esta pantalla está restringido solo a usuarios autenticados.  

![Image](https://github.com/user-attachments/assets/01977377-848e-4211-b48a-38b61f7f59a8)  

![Image](https://github.com/user-attachments/assets/650b869f-3ff6-4bbd-9347-5f827530a579)  

---

## Actualización en Firebase

Cuando un usuario se registra, sus datos (como nombre y correo) se almacenan automáticamente en Firebase Authentication.  
<img width="363" height="441" alt="Image" src="https://github.com/user-attachments/assets/e26ef099-0292-42f6-8c00-142fa3477ce7" />

---
