import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';
import { QueryClient, QueryClientProvider } from 'react-query';
import store from '../store';
import Login from '../Components/Login';
import '@testing-library/jest-dom/extend-expect';
import axios from 'axios';
import Signup from '../Components/Signup';
import ErrorPage from '../Components/ErrorPage';
import HomePage from '../Components/HomePage';
import ViewLoans from '../LoanManagerComponents/ViewLoans';
import LoanRequest from '../LoanManagerComponents/LoanRequest';
import LoanForm from '../LoanManagerComponents/LoanForm';
import ViewFeedback from '../LoanManagerComponents/ViewFeedback';

import ViewAllLoans from '../StudentComponents/ViewAllLoans';
import LoanApplicationForm from '../StudentComponents/LoanApplicationForm';
import StudentPostFeedback from '../StudentComponents/StudentPostFeedback';
import AppliedLoans from '../StudentComponents/AppliedLoans';
import CollegeForm from '../AdminComponents/CollegeForm';


jest.mock('axios');

// Setup QueryClient
const queryClient = new QueryClient();

describe('Login Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderLoginComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Login {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_login_component_renders_the_with_login_heading', () => {
    renderLoginComponent();

  
    const loginHeadings = screen.getAllByText(/Login/i);
    expect(loginHeadings.length).toBeGreaterThan(0);

  });


  test('frontend_login_component_displays_validation_messages_when_login_button_is_clicked_with_empty_fields', () => {
    renderLoginComponent();

    fireEvent.click(screen.getByRole('button', { name: /Login/i }));

    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
  });

   
});

describe('Signup Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderSignupComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Signup {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_signup_component_renders_with_signup_heading', () => {
    renderSignupComponent();

    const signupHeadings = screen.getAllByText(/Signup/i);
   expect(signupHeadings.length).toBeGreaterThan(0);

  });

  test('frontend_signup_component_displays_validation_messages_when_submit_button_is_clicked_with_empty_fields', () => {
    renderSignupComponent();

    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(screen.getByText('User Name is required')).toBeInTheDocument();
    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Mobile Number is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
    expect(screen.getByText('Confirm Password is required')).toBeInTheDocument();
  });

  test('frontend_signup_component_displays_error_when_passwords_do_not_match', () => {
    renderSignupComponent();

    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'password123' } });
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'password456' } });
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(screen.getByText('Passwords do not match')).toBeInTheDocument();
  });
});

describe('ErrorPage Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderErrorComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ErrorPage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_errorpage_component_renders_with_error_heading', () => {
    renderErrorComponent();
    const headingElement = screen.getByText(/Oops! Something Went Wrong/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_errorpage_component_renders_with_error_content', () => {
    renderErrorComponent();
    const paragraphElement = screen.getByText(/Please try again later./i);
    expect(paragraphElement).toBeInTheDocument();
  });
});
describe('Home Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderHomeComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <HomePage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_home_component_renders_with_heading', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/EDUINVESTOR/i);
    expect(headingElement.length).toBeGreaterThan(0);

  });
  test('frontend_home_component_renders_with_contact_us', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/Contact Us/i);
  });
});

describe('ViewAllLoans Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewAllLoansComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewAllLoans {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_viewallloans_student_component_renders_the_with_heading', () => {
    renderViewAllLoansComponent();

    const headingElement = screen.getByText(/Available Loans/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_viewallloans_student_component_renders_the_table', () => {
    renderViewAllLoansComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });
});

describe('LoanApplicationForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderLoanApplicationFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <LoanApplicationForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_loanapplicationform_student_component_renders_the_with_heading', () => {
    renderLoanApplicationFormComponent();

    const headingElement = screen.getByText(/Loan Application Form/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_loanapplicationform_student_component_displays_required_validation_messages', async () => {
    renderLoanApplicationFormComponent();

    // Fire submit event to trigger validation
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    // Await for the validation messages to appear
    expect(await screen.findByText(/Loan amount is required/i)).toBeInTheDocument();
    expect(await screen.findByText(/Loan purpose is required/i)).toBeInTheDocument();
    expect(await screen.findByText(/Income proof is required/i)).toBeInTheDocument();
  });

});

describe('StudentPostFeedback Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderStudentPostFeedbackComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <StudentPostFeedback {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_studentpostfeedback_student_component_renders_the_with_heading', () => {
    renderStudentPostFeedbackComponent();

    const headingElement = screen.getByText(/Submit Your Feedback/i);
    expect(headingElement).toBeInTheDocument();
  });
});


describe('AppliedLoans Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  
  const renderAppliedLoansComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <AppliedLoans {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_appliedloans_student_component_renders_the_with_heading', () => {
    renderAppliedLoansComponent();
    const headingElements = screen.getAllByText(/Applied Loans/i);
    expect(headingElements.length).toBeGreaterThan(1);

  });

test('frontend_appliedloans_student_component_renders_the_table', () => {
  renderAppliedLoansComponent();

  const tableElement = screen.getByRole('table');
  expect(tableElement).toBeInTheDocument();
});
});


describe('ViewLoans Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewLoansComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewLoans {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_viewloans_loanmanager_component_renders_the_with_heading', () => {
    renderViewLoansComponent();

    const headingElement = screen.getAllByText(/Loans/i);
    expect(headingElement.length).toBeGreaterThan(1);
  });

  test('frontend_viewloans_loanmanager_component_renders_the_table', () => {
    renderViewLoansComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });
});

describe('LoanRequest Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderLoanRequestComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <LoanRequest {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_loanrequest_loanmanager_component_renders_the_with_heading', () => {
    renderLoanRequestComponent();

    const headingElement = screen.getByText(/Loan Requests for Approval/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_loanrequest_loanmanager_component_renders_the_table', () => {
    renderLoanRequestComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });
});

describe('LoanForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderLoanFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <LoanForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_loanform_loan_manager_component_renders_the_with_create_heading', () => {
    renderLoanFormComponent();

    const headingElement = screen.getByText(/Create New Loan/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_loanform_loan_manager_component_displays_required_validation_messages', async () => {
    renderLoanFormComponent();

    // Click to trigger validation
    fireEvent.click(screen.getByRole('button', { name: /Add Loan/i }));

    // Use regular expressions to find text, making it more robust against subtle differences
    expect(await screen.findByText(/Loan type is required/i)).toBeInTheDocument();
    expect(await screen.findByText(/Description is required/i)).toBeInTheDocument();
  });
});

describe('ViewFeedback Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  
  const renderViewFeedbackComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewFeedback {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_viewfeedback_admin_component_renders_the_heading', () => {
    renderViewFeedbackComponent();

    const headingElement = screen.getByText(/Student Feedbacks/i);
    expect(headingElement).toBeInTheDocument();
  });
});

describe('CollegeForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });



  const renderCollegeFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <CollegeForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_collegeform_admin_component_renders_with_create_heading', () => {
    renderCollegeFormComponent();

    const headingElement = screen.getByText(/Create New College/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_collegeform_admin_component_displays_required_validation_messages', async () => {
    renderCollegeFormComponent();

    fireEvent.click(screen.getByRole('button', { name: /Add College/i }));

    expect(await screen.findByText('College name is required')).toBeInTheDocument();
    expect(screen.getByText('Address is required')).toBeInTheDocument();
    expect(screen.getByText('Contact number is required')).toBeInTheDocument();
    expect(screen.getByText('Courses are required')).toBeInTheDocument();
  });
});
