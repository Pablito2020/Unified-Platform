package data;

import exceptions.BadFormatPasswordException;

public final class Password {

    private final String password;

    public Password(String password) throws BadFormatPasswordException {
        if (password == null)
            throw new NullPointerException("Password shouldn't reference to null");
        if (password.length() < 8 || password.length() > 20)
            throw new BadFormatPasswordException("Password must be 8-20 characters long");
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password other = (Password) o;
        return password.equals(other.password);
    }

    @Override
    public String toString() {
        return "Password{" + "password='" + password + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }
}
