import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './AppliedColleges.css'

const StudentAppliedColleges = ({token,userId}) => {
 
   const [loading,setLoading] = useState(false);
   const [datas,setData] = useState([]);
  
useEffect(()=> {
const fetchData = async () => {
    return;
    setLoading(true);
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
      const response = await axios.get(`${baseUrl}/api/colleges/${userId}`,{ headers });
      setData(response.data);
    }catch(error){
        alert("Failed to Fetch Data:"+error);
    }
    finally{
        setLoading(false);
    }
};

//fetch 
fetchData();
},[token,userId]);
 
return (

    <div className='form-container'>
    <div className='form-box'>
        <h2>View Applied Colleges</h2> 
            <table>
            <thead> 
                <th>College Id</th>
                    <th>College Name</th>  
                    <th>Address</th>   
                    <th>Contact Number</th> 
                    <th>Email</th> 
                    <th>website</th> 
                    <th>courses</th> 
                    <th>status</th> 
            </thead>
            <tbody>
          

                {datas.map((college) => (
                    <tr>
                    <td>{college.collegeId}</td>
                    <td>{college.collegeName}</td> 
                    <td>{college.address}</td> 
                    <td>{college.contactNumber}</td> 
                    <td>{college.email}</td> 
                    <td>{college.website}</td>
                    <td>{college.courses}</td>
                    <td>{college.status}</td>
                    </tr>
                ))
                }    
            </tbody>
        </table>
    
    </div>
   </div>

);

};

export default StudentAppliedColleges;