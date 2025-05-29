import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './ViewAllLoans.css'
const ViewAllStudentLoans = ({token}) => {
 
   const [loading,setLoading] = useState(false);
   const [datas,setData] = useState([]);
  
useEffect(()=> {
const fetchData = async () => {
    
    setLoading(true);
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
      const response = await axios.get(`${baseUrl}/api/colleges`,{ headers });
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
},[token]);
 
return (

    <div className='form-container'>
    <div className='form-box'>
        <h2>Available Loans</h2> 
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
          

                {datas.map((loan) => (
                    <tr>
                    <td>{loan.collegeId}</td>
                    <td>{loan.collegeName}</td> 
                    <td>{loan.address}</td> 
                    <td>{loan.contactNumber}</td> 
                    <td>{loan.email}</td> 
                    <td>{loan.website}</td>
                    <td>{loan.courses}</td>
                    <td>{loan.status}</td>
                    </tr>
                ))
                }    
            </tbody>
        </table>
    </div>
   </div>

);

};

export default ViewAllStudentLoans;