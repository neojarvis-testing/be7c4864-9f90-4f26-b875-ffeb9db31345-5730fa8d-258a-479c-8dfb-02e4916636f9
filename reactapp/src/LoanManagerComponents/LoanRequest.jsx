import React,{useEffect, useState} from 'react';
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './LoanRequest.css'

const LoanRequest = ({token}) => {
 
    const [loading,setLoading] = useState(false);
    const [datas,setData] = useState([]);
//load Data
 useEffect(()=> {
 const fetchData = async () => {
     
     setLoading(true);
     try{
         const headers = {
             "Authorization":`Bearer ${token}`,
             "Content-Type":"application/json"
         }
       //Please Put Approve 
       const response = await axios.get(`${baseUrl}/api/loanapplications`,{ headers });
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
  

 //approve data
const handleApprove = async(id) => {
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
      //Please Put Approve 
      const response = await axios.put(`${baseUrl}/api/collegeApproveUrl?id=${token}`,{ headers });
      setData(response.data);
    }catch(error){
        alert("Failed to Fetch Data:"+error);
    }
    finally{
        setLoading(false);
    }
};


 //reject data
 const handleReject = async(id) => {
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
      //Please Put Approve 
      const response = await axios.put(`${baseUrl}/api/collegeApproveUrl?id=${token}`,{ headers });
      setData(response.data);
    }catch(error){
        alert("Failed to Fetch Data:"+error);
    }
    finally{
        setLoading(false);
    }
}


 return (
 
     <div className='form-container'>
     <div className='form-box'>
         <h1>Loan Requests for Approval</h1> 
         
             <table>
             <thead> 
                 <th>Loan Application Id</th>
                     <th>Applied Date</th>  
                     <th>Student Id</th>  
                     <th>Account Holder Name</th>   
                     <th>Loan Amount</th>  
                     <th>Tenure Months</th>  
                     <th>Employment Status</th>  
                     <th>#</th> 
                     <th>#</th> 
             </thead>
             <tbody>
           
 
                 {datas.map((college) => (
                     <tr>
                     <td>{college.loanApplicationId}</td>
                     <td>{college.applicationDate}</td> 
                     <td>{college.userId}</td>
                     <td>{college.accountHolder}</td> 
                     <td>{college.loanAmount}</td> 
                     <td>{college.tenureMonths}</td> 
                     <td>{college.employmentStatus}</td>
                     <td><button onClick={()=>handleApprove(college.collegeId)} value="Approve">Approve</button></td>
                     <td><button onClick={()=>handleReject(college.collegeId)}  value="Reject">Reject</button></td>
                     </tr>
                 ))
                 }    
             </tbody>
         </table>
 
     </div>
    </div>
 
 );
 
 };


export default LoanRequest;