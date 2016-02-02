/**
 * 
 */
package pt.mleiria.vo;

/**
 * @author manuel
 *
 */
public class User {

	private final String username;
	private final int id;
        private final String name;
	
	public User(final String username, final int id, final String name) {
		this.username = username;
		this.id = id;
                this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}
	public String getName(){
            return name;
        }
}
