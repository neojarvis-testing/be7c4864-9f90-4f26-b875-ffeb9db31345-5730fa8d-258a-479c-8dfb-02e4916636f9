import React,{useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './CollegeApproval.css'

const CollegeApproval = ({token}) => {
 
    const [loading,setLoading] = useState(false);
    const [datas,setData] = useState([]);
    const navigate = useNavigate();
//load Data
 
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
 useEffect(()=> {fetchData();},[token]);

 
  

 //approve data
const handleApprove = async(id) => {
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json",
            "status":"Active"
        }
        const body = {} 
        await axios.put(`${baseUrl}/api/colleges/${id}/approve`,{body},{ headers });
      alert("Approve Success !");
      await fetchData();
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
            "Content-Type":"application/json",
            "status":"Rejected"
        }

        const body = {}
 
      const response = await axios.put(`${baseUrl}/api/colleges/${id}/approve`,body,{ headers });
      alert("Reject Success !");
      await fetchData();
    }catch(error){
        alert("Failed to Fetch Data:"+error);
    }
    finally{
        setLoading(false);
    }
}


//delete data

 //reject data
 const handleDelete = async(id) => {
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json",
            "status":"Rejected"
        } 

      const response = await axios.delete(`${baseUrl}/api/colleges/${id}`,{ headers });
      alert("Delete Success !");
      await fetchData();
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
         <h2>College Approval page</h2> 
         {loading?(
             <p>Loading</p>
         ) : datas.length === 0 ? (
             <p>No Data Found</p>
         ) :
         (
 
             <table>
             <thead> 
                 <th>College Id</th>
                     <th>College Name</th>  
                     <th>Adderss</th>   
                     <th>Contact Number</th> 
                     <th>Email</th> 
                     <th>Web Address</th> 
                     <th>Course</th> 
                     <th>status</th>
                     <th>Status</th> 
                     <th>#</th> 
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
                     <td><button onClick={()=>handleApprove(college.collegeId)} value="Approve">Approve</button></td>
                     <td><button onClick={()=>handleReject(college.collegeId)}  value="Reject">Reject</button></td>
                     <td><button onClick={()=>handleDelete(college.collegeId)}  value="Reject">Delete</button></td>
                     </tr>
                 ))
                 }    
             </tbody>
         </table>
 
 
         )
     }
     
 
     </div>
    </div>
 
 );
 
 };


export default CollegeApproval;