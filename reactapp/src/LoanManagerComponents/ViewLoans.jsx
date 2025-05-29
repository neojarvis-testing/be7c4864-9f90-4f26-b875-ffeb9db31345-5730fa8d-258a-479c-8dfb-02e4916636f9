import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './ViewLoans.css'
const ViewLoans = ({token}) => {
 
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
        <h2>View Loans</h2>  
        <h2>View Loans</h2>  
            <table>
            <thead> 
                <th>Loan Id</th>
                    <th>Loan Name</th>  
                    <th>x</th>   
                    <th>fill data</th> 
                    <th>fill data</th> 
                    <th>fill data</th> 
                    <th>fill data</th> 
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

export default ViewLoans;