import React from "react";
import Form from "react-bootstrap/Form";
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from "react-bootstrap/Button";
import "./styles/login.css"

export default function Login() {
    return (
        <div className="Login p-2 rounded-lg w-25 h-50 d-flex align-items-center justify-content-center" >
            <Form  className="w-75 h-auto">
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>E-mail</Form.Label>
                    <Form.Control required type="email" placeholder="janedoe@email.com" />
                </Form.Group>
                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control required type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Remember me" />
                </Form.Group>
                <Button variant="light" type="submit" className="w-100">
                    Login
                </Button>
            </Form>
        </div>
    );
}