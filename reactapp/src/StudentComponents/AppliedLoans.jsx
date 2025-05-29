import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './AppliedLoans.css'

const StudentAppliedLoans = ({token,userId}) => {
 
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
            <h2>Student Applied Loans</h2> 
            <h2>Student Applied Loans</h2> 
            <table>
            <thead> 
                <th>Id</th>
                    <th>Loan Name</th>  
                    <th>fill</th>   
                    <th>fill</th> 
                    <th>fill</th> 
                    <th>fill</th> 
                    <th>fill</th> 
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

export default StudentAppliedLoans;