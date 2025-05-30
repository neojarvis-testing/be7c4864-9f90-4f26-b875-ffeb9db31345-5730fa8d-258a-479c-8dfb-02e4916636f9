import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './ViewAllColleges.css'


const ViewAllStudentColleges = ({token}) => {
 
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
        <h2>View All Colleges</h2> 
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
                    <th>Address</th>   
                    <th>Contact Number</th> 
                    <th>Email</th> 
                    <th>website</th> 
                    <th>courses</th>  
            </thead>
            <tbody>
          

                {datas
                .filter(s=>s.status==="Active")
                .map((college) => (
                    <tr>
                    <td>{college.collegeId}</td>
                    <td>{college.collegeName}</td> 
                    <td>{college.address}</td> 
                    <td>{college.contactNumber}</td> 
                    <td>{college.email}</td> 
                    <td>{college.website}</td>
                    <td>{college.courses}</td> 
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

export default ViewAllStudentColleges;