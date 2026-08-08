import React from "react";

function FormInput({ label, type, value, onChange }) {
    return (
        <div className="form-group">
            <label>{label}</label>
            <input
                type={type}
                value={value}
                onChange={onChange}
                className="form-control"
                required
            />
        </div>
    );
}

export default FormInput;
