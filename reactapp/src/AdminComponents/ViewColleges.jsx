<<<<<<< HEAD
=======
import React,{useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './ViewColleges.css'
const ViewColleges = () => {
 
    const [loading,setLoading] = useState(false);
   
 
            // Call API
const headers = {
    "Authorization":`Bearer ${token}`,
    "Content-Type":"application/json"

}



return (

    <div className='form-container'>
    <div className='form-box'>
        <h2>View College</h2>

        <input type='text' name='name' placeholder='College Name' required />
        <br/>
        <table>
            <thead>
                <tr>
                    <th>head 1</th>
                    <th>head 2</th>
                    <th>item 3</th>
                    <th>item 4</th>
                    <th>item 5</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                    <th>body 1</th>
                    <th>body 2</th>
                    <th>body 3</th>
                    <th>body 4</th>
                    <th>body 5</th>
                </tr>
            </tbody>
        </table>
    </div>
   </div>

);

};

export default ViewColleges;
>>>>>>> 31f5b23b19564ba6766de4f13bc885fe42bc3612
