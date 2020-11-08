# loadshareassignment


Have created project using MVP architecture. 

Libraries used. 
1. Retrofit(https://github.com/square/retrofit)
2. TedPermission(https://github.com/ParkSangGwon/TedPermission)
3. Glide (https://github.com/bumptech/glide)
4. Realm.io(https://github.com/realm/realm-java)

It takes input as Image , First Name and Last Name and saves in Realm Database. 
And shows in on the screen with the help of realm database. 

It makes a HTTP request to https://ifsc.razorpay.com/ to get the details of IFSC. And stores the data in Realm Database. 
If the same data is queried then API call is not made and loaded from Database.


