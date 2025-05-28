import React, { useState } from 'react'

const LoanApplicationForm = () => {

  const [formData, setFormData] = useState({
    loanType: '',
    loanAmount: '',
    loanPurpose: '',
    incomeProof: ''
  })

  const [errors, setErrors] = useState(
    {
      loanType: '',
      loanAmount: '',
      loanPurpose: '',
      incomeProof: ''
    }
  )

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData, [name]: value
    }))

    if (errors[name]) {
      setErrors((prevError) => ({
        ...prevError,
        [name]: undefined
      }))
    }
  }

  const validateForm = () => {
    let newErrors = {};
    if (!formData.loanType.trim()) {
      newErrors.loanType = "College name is required"
    }
    if (!formData.loanAmount.trim()) {
      newErrors.loanAmount = "Loan amount is required"
    }
    if (!formData.loanPurpose.trim()) {
      newErrors.loanPurpose = "Loan purpose is required"
    }
    if (!formData.incomeProof.trim()) {
      newErrors.incomeProof = "Income proof is required"
    }
    setErrors(newErrors)
    return Object.keys(newErrors).length === 0;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const isValid = validateForm();
    if (isValid) {
      // logic
    } else {
      // logic
    }
  }


  return (
    <div className='form-container'>
      {/* {check ? (<div> College name is required Address is required Contact number is required Courses are required</div>) : (<div> d</div>)} */}

      <form className='form-box' onSubmit={handleSubmit}>
        <h2>Loan Application Form</h2>
        <input type='text' name='loanType' placeholder='Loan Type' value={formData.loanType} onChange={handleChange} />
        {errors.loanType && <div>{errors.loanType}</div>}
        <input type='text' name='loanAmount' placeholder='Loan Amount' value={formData.loanAmount} onChange={handleChange} />
        {errors.loanAmount && <div>{errors.loanAmount}</div>}
        <input type='text' name='loanPurpose' placeholder='Loan Purpose' value={formData.loanPurpose} onChange={handleChange} />
        {errors.loanPurpose && <div>{errors.loanPurpose}</div>}
        <input type='text' name='incomeProof' placeholder='Income Proof' value={formData.incomeProof} onChange={handleChange} />
        {errors.incomeProof && <div>{errors.incomeProof}</div>}

        <button type='submit' name="Submit" role='button'>Submit</button>
      </form>
    </div>
  )
}

export default LoanApplicationForm