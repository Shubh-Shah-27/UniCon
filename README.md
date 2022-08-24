# UniCon
This is a simple Dollar to Rupee Converter App which uses an API to get the latest value and uses SharedPreference to store the value for offline access

Concepts:
1) API Call using Volley
2) SharedPreference

Project Flow:
<ul>
<li>API Call is made on starting the application -> Successful: The new value is stored in the SharedPreference</li>
<li>User enters the value and presses Submit button</li>
<li>On button press, value is fetched from the SharedPreference</li>
<li>Corresponding rupees value is calculated and displayed</li>
</ul>
