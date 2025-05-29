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
      const response = await axios.get(`${baseUrl}/api/loans`,{ headers });
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
 
                <th>Loan Type</th>
                    <th>description</th>  
                    <th>interestRate</th>   
                    <th>maximumAmount</th> 
                    <th>minimumAmount</th> 
                    <th>minimumTenure</th> 
                    <th>processingFee</th> 
                    <th>gracePeriod</th> 
                    <th>latePaymentFee</th> 
            </thead>
            <tbody>
          
                {datas.map((loan) => (
                    <tr>
                    <td>{loan.loanType}</td>
                    <td>{loan.description}</td> 
                    <td>{loan.interestRate}</td> 
                    <td>{loan.maximumAmount}</td> 
                    <td>{loan.minimumAmount}</td> 
                    <td>{loan.processingFee}</td>
                    <td>{loan.prepaymentPenalty}</td>
                    <td>{loan.gracePeriod}</td>
                    <td>{loan.latePaymentFee}</td>
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