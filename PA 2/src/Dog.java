/**
 * Dog.java - dog model which can draw itself in OpenGL
 * 
 * History:
 * 
 * 18 February 2011
 * 
 * - added documentation
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 16 January 2008
 * 
 * modified to dog class by Stan Sclaroff in February, 2012
 * 
 * (Stan Sclaroff <sclaroff@cs.bu.edu>)
 */


import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A model of a dog containing an ellipsoid for the body, four legs, a neck, head, and tail.
 * 
 * This class provides methods for rotating individual joints on individual
 * legs as well.
 * 
 * @author Stan Sclaroff
 * @since Spring 2012
 */
public class Dog {

  /** The x-, y-, or z-axis. */
  enum AxisType {
    X, Y, Z
  }

  /** The body part to toggle. */
  enum BodyPartType {
    FRONT_LEFT_LEG, FRONT_RIGHT_LEG, REAR_LEFT_LEG, REAR_RIGHT_LEG, NECK, HEAD, TAIL
  }

  /** The type of joint at which to enable rotation. */
  enum JointType {
    PAW, UPPER, LOWER
  }

  /**
   * The change in rotation angle (in degrees) to apply on each rotation update.
   */
  private static final double DELTA_ANGLE = 1.0;

  /** The currently active parts. */
  private final boolean active_parts[] = new boolean[] { false, false,
      false, false, false, false, false };
  /** The currently active joint type. */
  private JointType active_joint = JointType.PAW;
  /** The currently active axis of rotation. */
  private AxisType active_rotation_axis = AxisType.X;
  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int dog_object;
  private int front_left_upper;
  private int front_left_lower;
  private int front_left_paw;
  private int front_right_upper;
  private int front_right_lower;
  private int front_right_paw;
  private int rear_left_upper;
  private int rear_left_lower;
  private int rear_left_paw;
  private int rear_right_upper;
  private int rear_right_lower;
  private int rear_right_paw;
  private int neck;
  private int head;
  private int tail;
  
  private float body_anglex;
  private float body_angley;
  private float body_anglez;
  private int front_left_upper_anglex;
  private int front_left_upper_angley;
  private int front_left_upper_anglez;
  private int front_left_lower_anglex;
  private int front_left_lower_angley;
  private int front_left_lower_anglez;
  private int front_left_paw_anglex;
  private int front_left_paw_angley;
  private int front_left_paw_anglez;
  private int front_right_upper_anglex;
  private int front_right_upper_angley;
  private int front_right_upper_anglez;
  private int front_right_lower_anglex;
  private int front_right_lower_angley;
  private int front_right_lower_anglez;
  private int front_right_paw_anglex;
  private int front_right_paw_angley;
  private int front_right_paw_anglez;
  private int rear_left_upper_anglex;
  private int rear_left_upper_angley;
  private int rear_left_upper_anglez;
  private int rear_left_lower_anglex;
  private int rear_left_lower_angley;
  private int rear_left_lower_anglez;
  private int rear_left_paw_anglex;
  private int rear_left_paw_angley;
  private int rear_left_paw_anglez;
  private int rear_right_upper_anglex;
  private int rear_right_upper_angley;
  private int rear_right_upper_anglez;
  private int rear_right_lower_anglex;
  private int rear_right_lower_angley;
  private int rear_right_lower_anglez;
  private int rear_right_paw_anglex;
  private int rear_right_paw_angley;
  private int rear_right_paw_anglez;
  private int neck_anglex;
  private int neck_angley;
  private int neck_anglez;
  private int head_anglex;
  private int head_angley;
  private int head_anglez;
  private int tail_anglex;
  private int tail_angley;
  private int tail_anglez;
  
  private int command;
  
  /**
   * Whether the state of the dog model has changed since the last update.
   * 
   * This is initially true so that the dog will show up initially.
   */
  private boolean state_has_changed = true;

  private final GLUT glut;

  private float head_translatex;

  private float head_translatey;

private float rear_upper_translatex;

private float rear_upper_translatey;

private float rear_lower_translatex;

private float rear_lower_translatey;

private float front_upper_translatex;

private float front_upper_translatey;

private float front_lower_translatex;

private float front_lower_translatey;

private float front_paw_translatex;

private float front_paw_translatey;

private float rear_paw_translatex;

private float rear_paw_translatey;

  /**
   * Instantiates this dog with access to the specified OpenGL utility toolkit
   * object.
   * 
   * @param glut
   *          The OpenGL utility toolkit object.
   */
  public Dog(final GLUT glut) {
    this.glut = glut;
  }

  /**
   * Decreases the rotation of the currently selected joint on the currently
   * selected body parts by {@value #DELTA_ANGLE} degrees.
   */
  public void rotateActiveJointsForward() {
    this.state_has_changed = true; // flag to recreate the display list

    // you will need to rewrite this function
    //System.out.println("Dog::decrement_rotation_angle()");    

    if (active_parts[0]) {
    	if (active_joint == JointType.PAW) {
    		if (rear_left_paw_anglex > -5 && rear_left_paw_anglex <= 5) {
    			if (active_rotation_axis == AxisType.X) rear_left_paw_anglex--; 
    		}
    		if (rear_left_paw_angley > -5 && rear_left_paw_angley <= 5) {
    			if (active_rotation_axis == AxisType.Y) rear_left_paw_angley--;
    		}
    		if (rear_left_paw_anglez > -3 && rear_left_paw_anglez <= 1) {
            	if (active_rotation_axis == AxisType.Z) rear_left_paw_anglez--;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (rear_left_lower_anglex > -3 && rear_left_lower_anglex <= 3) {
    			if (active_rotation_axis == AxisType.X) { rear_left_lower_anglex--; rear_left_paw_anglex--; }
        	}
        	if (rear_left_lower_angley > -10 && rear_left_lower_angley <= 5) {
        	    if (active_rotation_axis == AxisType.Y) { rear_left_lower_angley--; rear_left_paw_angley--; }
        	}
        	if (rear_left_lower_anglez > -5 && rear_left_lower_anglez <= 3) {
        		if (active_rotation_axis == AxisType.Z) { rear_left_lower_anglez--; rear_left_paw_anglez--; }
        	}
        }
        if (active_joint == JointType.UPPER) {
        	if (rear_left_upper_anglex > -12 && rear_left_upper_anglex <= 12) {
        		if (active_rotation_axis == AxisType.X) { rear_left_upper_anglex--; rear_left_lower_anglex--; rear_left_paw_anglex--; }
        	}
        	if (rear_left_upper_angley > -5 && rear_left_upper_angley <= 5) {
        		if (active_rotation_axis == AxisType.Y) { rear_left_upper_angley--; rear_left_lower_angley--; rear_left_paw_angley--; }
        	}
        	if (rear_left_upper_anglez > -12 && rear_left_upper_anglez <= 20) {
        		if (active_rotation_axis == AxisType.Z) { rear_left_upper_anglez--; rear_left_lower_anglez--; rear_left_paw_anglez--; }
        	}
        }
    }
    if (active_parts[1]) {
    	if (active_joint == JointType.PAW) {
    		if (rear_right_paw_anglex > -5 && rear_right_paw_anglex <= 5) {
    			if (active_rotation_axis == AxisType.X) rear_right_paw_anglex--; 
    		}
    		if (rear_right_paw_angley > -5 && rear_right_paw_angley <= 5) {
    			if (active_rotation_axis == AxisType.Y) rear_right_paw_angley--;
    		}
    		if (rear_right_upper_anglez > -3 && rear_right_paw_anglez <= 1) {
            	if (active_rotation_axis == AxisType.Z) rear_right_paw_anglez--;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (rear_right_lower_anglex > -3 && rear_right_lower_anglex <= 3) {
    			if (active_rotation_axis == AxisType.X) { rear_right_lower_anglex--; rear_right_paw_anglex--; }
        	}
        	if (rear_right_lower_angley > -10 && rear_right_lower_angley <= 5) {
        	    if (active_rotation_axis == AxisType.Y) { rear_right_lower_angley--; rear_right_paw_angley--; }
        	}
        	if (rear_right_lower_anglez > -5 && rear_right_lower_anglez <= 3) {
        		if (active_rotation_axis == AxisType.Z) { rear_right_lower_anglez--; rear_right_paw_anglez--; }
        	}
        }
        if (active_joint == JointType.UPPER) {
        	if (rear_right_upper_anglex > -12 && rear_right_upper_anglex <= 12) {
        		if (active_rotation_axis == AxisType.X) { rear_right_upper_anglex--; rear_right_lower_anglex--; rear_right_paw_anglex--; }
        	}
        	if (rear_right_upper_angley > -5 && rear_right_upper_angley <= 5) {
        		if (active_rotation_axis == AxisType.Y) { rear_right_upper_angley--; rear_right_lower_angley--; rear_right_paw_angley--; }
        	}
        	if (rear_right_upper_anglez > -12 && rear_right_upper_anglez <= 20) {
        		if (active_rotation_axis == AxisType.Z) { rear_right_upper_anglez--; rear_right_lower_anglez--; rear_right_paw_anglez--; }
        	}
        }
    }
    if (active_parts[2]) {
    	if (active_joint == JointType.PAW) {
    		if (front_left_paw_anglex > -5 && front_left_paw_anglex <= 5) {
    			if (active_rotation_axis == AxisType.X) front_left_paw_anglex--;
    		}
    		if (front_left_paw_angley > -5 && front_left_paw_angley <= 5) {
    			if (active_rotation_axis == AxisType.Y) front_left_paw_angley--;
    		}
    		if (front_left_paw_anglez > -3 && front_left_paw_anglez <= 1) {
    			if (active_rotation_axis == AxisType.Z) front_left_paw_anglez--;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (front_left_lower_anglex > -3 && front_left_lower_anglex <= 3) {
        		if (active_rotation_axis == AxisType.X) { front_left_lower_anglex--; front_left_paw_anglex--; }
        	}
	        if (front_left_lower_angley > -10 && front_left_lower_angley <= 5) {
				if (active_rotation_axis == AxisType.Y) { front_left_paw_angley--; front_left_paw_angley--; }
			}
			if (front_left_lower_anglez > -5 && front_left_lower_anglez <= 3) {
				if (active_rotation_axis == AxisType.Z) { front_left_paw_anglez--; front_left_paw_anglez--; }
			}
        }
        if (active_joint == JointType.UPPER) {
        	if (front_left_upper_anglex > -8 && front_left_upper_anglex <= 8) {
    			if (active_rotation_axis == AxisType.X) { front_left_upper_anglex--; front_left_lower_anglex--; front_left_paw_anglex--; }
    		}
        	if (front_left_upper_angley > -5 && front_left_upper_angley <= 5) {
				if (active_rotation_axis == AxisType.Y) { front_left_upper_angley--; front_left_paw_angley--; front_left_paw_angley--; }
			}
        	if (front_left_upper_anglez > -10 && front_left_upper_anglez <= 15) {
        		if (active_rotation_axis == AxisType.Z) { front_left_upper_anglez--; front_left_paw_anglez--; front_left_paw_anglez--; }
        	}
        }
    }
    if (active_parts[3]) {
    	if (active_joint == JointType.PAW) {
    		if (front_right_paw_anglex > -5 && front_right_paw_anglex <= 5) {
    			if (active_rotation_axis == AxisType.X) front_right_paw_anglex--;
    		}
    		if (front_right_paw_angley > -5 && front_right_paw_angley <= 5) {
    			if (active_rotation_axis == AxisType.Y) front_right_paw_angley--;
    		}
    		if (front_right_paw_anglez > -3 && front_right_paw_anglez <= 1) {
    			if (active_rotation_axis == AxisType.Z) front_right_paw_anglez--;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (front_right_lower_anglex > -3 && front_right_lower_anglex <= 3) {
        		if (active_rotation_axis == AxisType.X) { front_right_lower_anglex--; front_right_paw_anglex--; }
        	}
	        if (front_right_lower_angley > -10 && front_right_lower_angley <= 5) {
				if (active_rotation_axis == AxisType.Y) { front_right_lower_angley--; front_right_paw_angley--; }
	        }
			if (front_right_lower_anglez > -5 && front_right_lower_anglez <= 3) {
				if (active_rotation_axis == AxisType.Z) { front_right_lower_anglez--; front_right_paw_anglez--; }
			}
        }
        if (active_joint == JointType.UPPER) {
        	if (front_right_upper_anglex > -12 && front_right_upper_anglex <= 12) {
    			if (active_rotation_axis == AxisType.X) { front_right_upper_anglex--; front_right_lower_anglex--; front_right_paw_anglex--; }
    		}
        	if (front_right_upper_angley > -5 && front_right_upper_angley <= 5) {
				if (active_rotation_axis == AxisType.Y) { front_right_upper_angley--; front_right_lower_angley--; front_right_paw_angley--; }
        	}
        	if (front_right_upper_anglez > -12 && front_right_upper_anglez <= 20) {
        		if (active_rotation_axis == AxisType.Z) { front_right_upper_anglez--; front_right_lower_anglez--; front_right_paw_anglez--; }
        	} 
        }
    }
    if (active_parts[4]) {
    	if (head_anglex > -20 && head_anglex <= 20) {
    		if (active_rotation_axis == AxisType.X) head_anglex--;
    	}
        if (head_angley > -15 && head_angley <= 15) {
    		if (active_rotation_axis == AxisType.Y) head_angley--;
		}
        if (head_anglez > -15 && head_anglez <= 5) {
    		if (active_rotation_axis == AxisType.Z)	head_anglez--;
		}
    }
    if (active_parts[5]) {
    	if (tail_anglex > -20 && tail_anglex <= 20) {
    		if (active_rotation_axis == AxisType.X) tail_anglex--;
    	}	
    	if (tail_angley > -10 && tail_angley <= 10) {
    		if (active_rotation_axis == AxisType.Y) tail_angley--;
    	}
    	if (tail_anglez > -15 && tail_anglez <= 15) {
    		if (active_rotation_axis == AxisType.Z)	tail_anglez--;
    	}
    }
    if (active_parts[6]) {
    	if (neck_anglex > -10 && neck_anglex <= 10) {
    		if (active_rotation_axis == AxisType.X) neck_anglex--;
		}
    	if (neck_angley > -10 && neck_angley <= 10) {
    		if (active_rotation_axis == AxisType.Y) neck_angley--;
		}
    	if (neck_anglez > -10 && neck_anglez <= 10) {
    		if (active_rotation_axis == AxisType.Z)	neck_anglez--;
		}
    }
  }

  /**
   * Increases the rotation of the currently selected joint on the currently
   * selected body parts by {@value #DELTA_ANGLE} degrees.
   */
  public void rotateActiveJointsBackward() {
    this.state_has_changed = true; // flag to recreate the display list

    // you will need to rewrite this function
    //System.out.println("Dog::increment_rotation_angle()");
    
    if (active_parts[0]) {
    	if (active_joint == JointType.PAW) {
    		if (rear_left_paw_anglex >= -5 && rear_left_paw_anglex < 5) {
    			if (active_rotation_axis == AxisType.X) rear_left_paw_anglex++; 
    		}
    		if (rear_left_paw_angley >= -5 && rear_left_paw_angley < 5) {
    			if (active_rotation_axis == AxisType.Y) rear_left_paw_angley++;
    		}
    		if (rear_left_paw_anglez >= -3 && rear_left_paw_anglez < 1) {
            	if (active_rotation_axis == AxisType.Z) rear_left_paw_anglez++;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (rear_left_lower_anglex >= -3 && rear_left_lower_anglex < 3) {
    			if (active_rotation_axis == AxisType.X) { rear_left_lower_anglex++; rear_left_paw_anglex++; }
        	}
        	if (rear_left_lower_angley >= -10 && rear_left_lower_angley < 5) {
        	    if (active_rotation_axis == AxisType.Y) { rear_left_lower_angley++; rear_left_paw_angley++; }
        	}
        	if (rear_left_lower_anglez >= -5 && rear_left_lower_anglez < 3) {
        		if (active_rotation_axis == AxisType.Z) { rear_left_lower_anglez++; rear_left_paw_anglez++; }
        	}
        }
        if (active_joint == JointType.UPPER) {
        	if (rear_left_upper_anglex >= -12 && rear_left_upper_anglex < 12) {
        		if (active_rotation_axis == AxisType.X) { rear_left_upper_anglex++; rear_left_lower_anglex++; rear_left_paw_anglex++; }
        	}
        	if (rear_left_upper_angley >= -5 && rear_left_upper_angley < 5) {
        		if (active_rotation_axis == AxisType.Y) { rear_left_upper_angley++; rear_left_lower_angley++; rear_left_paw_angley++; }	
        	}
        	if (rear_left_upper_anglez >= -12 && rear_left_upper_anglez < 20) {
        		if (active_rotation_axis == AxisType.Z) { rear_left_upper_anglez++; rear_left_lower_anglez++; rear_left_paw_anglez++; }
        	}
        }
    }
    if (active_parts[1]) {
    	if (active_joint == JointType.PAW) {
    		if (rear_right_paw_anglex >= -5 && rear_right_paw_anglex < 5) {
    			if (active_rotation_axis == AxisType.X) rear_right_paw_anglex++; 
    		}
    		if (rear_right_paw_angley >= -5 && rear_right_paw_angley < 5) {
    			if (active_rotation_axis == AxisType.Y) rear_right_paw_angley++;
    		}
    		if (rear_right_upper_anglez >= -3 && rear_right_paw_anglez < 1) {
            	if (active_rotation_axis == AxisType.Z) rear_right_paw_anglez++;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (rear_right_lower_anglex >= -3 && rear_right_lower_anglex < 3) {
    			if (active_rotation_axis == AxisType.X) { rear_right_lower_anglex++; rear_right_paw_anglex++; }
        	}
        	if (rear_right_lower_angley >= -10 && rear_right_lower_angley < 5) {
        	    if (active_rotation_axis == AxisType.Y) { rear_right_lower_angley++; rear_right_paw_angley++; }
        	}
        	if (rear_right_lower_anglez >= -5 && rear_right_lower_anglez < 3) {
        		if (active_rotation_axis == AxisType.Z) { rear_right_lower_anglez++; rear_right_paw_anglez++; }
        	}
        }
        if (active_joint == JointType.UPPER) {
        	if (rear_right_upper_anglex >= -12 && rear_right_upper_anglex < 12) {
        		if (active_rotation_axis == AxisType.X) { rear_right_upper_anglex++; rear_right_lower_anglex++; rear_right_paw_anglex++; }
        	}
        	if (rear_right_upper_angley >= -5 && rear_right_upper_angley < 5) {
        		if (active_rotation_axis == AxisType.Y) { rear_right_upper_angley++; rear_right_lower_angley++; rear_right_paw_angley++; }
        	}
        	if (rear_right_upper_anglez >= -12 && rear_right_upper_anglez < 20) {
        		if (active_rotation_axis == AxisType.Z) { rear_right_upper_anglez++; rear_right_lower_anglez++; rear_right_paw_anglez++; }
        	}
        }
    }
    if (active_parts[2]) {
    	if (active_joint == JointType.PAW) {
    		if (front_left_paw_anglex >= -5 && front_left_paw_anglex < 5) {
    			if (active_rotation_axis == AxisType.X) front_left_paw_anglex++;
    		}
    		if (front_left_paw_angley >= -5 && front_left_paw_angley < 5) {
    			if (active_rotation_axis == AxisType.Y) front_left_paw_angley++;
    		}
    		if (front_left_paw_anglez >= -3 && front_left_paw_anglez < 1) {
    			if (active_rotation_axis == AxisType.Z) front_left_paw_anglez++;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (front_left_lower_anglex >= -3 && front_left_lower_anglex < 3) {
        		if (active_rotation_axis == AxisType.X) { front_left_lower_anglex++; front_left_paw_anglex++; }
        	}
	        if (front_left_lower_angley >= -10 && front_left_lower_angley < 5) {
				if (active_rotation_axis == AxisType.Y) { front_left_paw_angley++; front_left_paw_angley++; }
			}
			if (front_left_lower_anglez  >= -5 && front_left_lower_anglez < 3) {
				if (active_rotation_axis == AxisType.Z) { front_left_paw_anglez++; front_left_paw_anglez++; }
			}
        }
        if (active_joint == JointType.UPPER) {
        	if (front_left_upper_anglex >= -12 && front_left_upper_anglex < 12) {
    			if (active_rotation_axis == AxisType.X) { front_left_upper_anglex++; front_left_lower_anglex++; front_left_paw_anglex++; }
    		}
        	if (front_left_upper_angley >= -5 && front_left_upper_angley < 5) {
				if (active_rotation_axis == AxisType.Y) { front_left_upper_angley++; front_left_paw_angley++; front_left_paw_angley++; }
			}
        	if (front_left_upper_anglez >= -12 && front_left_upper_anglez < 20) {
        		if (active_rotation_axis == AxisType.Z) { front_left_upper_anglez++; front_left_paw_anglez++; front_left_paw_anglez++; }
        	}
        }
    }
    if (active_parts[3]) {
    	if (active_joint == JointType.PAW) {
    		if (front_right_paw_anglex >= -5 && front_right_paw_anglex < 5) {
    			if (active_rotation_axis == AxisType.X) front_right_paw_anglex++;
    		}
    		if (front_right_paw_angley >= -5 && front_right_paw_angley < 5) {
    			if (active_rotation_axis == AxisType.Y) front_right_paw_angley++;
    		}
    		if (front_right_paw_anglez >= -3 && front_right_paw_anglez < 1) {
    			if (active_rotation_axis == AxisType.Z) front_right_paw_anglez++;
    		}
    	}
        if (active_joint == JointType.LOWER) {
        	if (front_right_lower_anglex >= -3 && front_right_lower_anglex < 3) {
        		if (active_rotation_axis == AxisType.X) { front_right_lower_anglex++; front_right_paw_anglex++; }
        	}
	        if (front_right_lower_angley >= -10 && front_right_lower_angley < 5) {
				if (active_rotation_axis == AxisType.Y) { front_right_lower_angley++; front_right_paw_angley++; }
	        }
			if (front_right_lower_anglez >= -5 && front_right_lower_anglez < 3) {
				if (active_rotation_axis == AxisType.Z) { front_right_lower_anglez++; front_right_paw_anglez++; }
			}
        }
        if (active_joint == JointType.UPPER) {
        	if (front_right_upper_anglex >= -12 && front_right_upper_anglex < 12) {
    			if (active_rotation_axis == AxisType.X) { front_right_upper_anglex++; front_right_lower_anglex++; front_right_paw_anglex++; }
    		}
        	if (front_right_upper_angley >= -5 && front_right_upper_angley < 5) {
				if (active_rotation_axis == AxisType.Y) { front_right_upper_angley++; front_right_lower_angley++; front_right_paw_angley++; }
        	} 
        	if (front_right_upper_anglez >= -12 && front_right_upper_anglez < 20) {
        		if (active_rotation_axis == AxisType.Z) { front_right_upper_anglez++; front_right_lower_anglez++; front_right_paw_anglez++; }
        	}
        }
    }
    if (active_parts[4]) {
    	if (head_anglex >= -20 && head_anglex < 20) {
    		if (active_rotation_axis == AxisType.X) head_anglex++;
    	}
        if (head_angley >= -15 && head_angley < 15) {
    		if (active_rotation_axis == AxisType.Y) head_angley++;
		}
        if (head_anglez >= -15 && head_anglez < 5) {
    		if (active_rotation_axis == AxisType.Z)	head_anglez++;
		}
    }
    if (active_parts[5]) {
    	if (tail_anglex >= -20 && tail_anglex < 20) {
    		if (active_rotation_axis == AxisType.X) tail_anglex++;
    	}	
    	if (tail_angley >= -10 && tail_angley < 10) {
    		if (active_rotation_axis == AxisType.Y) tail_angley++;
    	}
    	if (tail_anglez >= -15 && tail_anglez < 15) {
    		if (active_rotation_axis == AxisType.Z)	tail_anglez++;
    	}
    }
    if (active_parts[6]) {
    	if (neck_anglex >= -10 && neck_anglex < 10) {
    		if (active_rotation_axis == AxisType.X) neck_anglex++;
		}
    	if (neck_angley >= -10 && neck_angley < 10) {
    		if (active_rotation_axis == AxisType.Y) neck_angley++;
		}
    	if (neck_anglez >= -10 && neck_anglez < 10) {
    		if (active_rotation_axis == AxisType.Z)	neck_anglez++;
		}
    }
  }
  
  /**
   * Draws the dog on the specified OpenGL object based on the current state.
   * 
   * @param gl
   *          The GL object with which to draw the dog.
   */
  public void draw(final GL gl) {
    
	gl.glPushMatrix();	  
		gl.glRotatef(this.body_anglex, 1, 0, 0);     
		gl.glRotatef(this.body_angley, 0, 1, 0); 
		gl.glRotatef(this.body_anglez, 0, 0, 1); 
		gl.glCallList(this.dog_object);
	gl.glPopMatrix();
    
    gl.glPushMatrix();
        gl.glRotatef(this.front_left_upper_anglex, 1, 0, 0);
        gl.glRotatef(this.front_left_upper_angley, 0, 1, 0);
        gl.glRotatef(this.front_left_upper_anglez, 0, 0, 1);
        gl.glTranslatef(this.front_upper_translatex, this.front_upper_translatey, 0);
        gl.glCallList(this.front_left_upper);	
    gl.glPopMatrix();
    
    gl.glPushMatrix();
        gl.glRotatef(this.front_left_lower_anglex, 1, 0, 0);
        gl.glRotatef(this.front_left_lower_angley, 0, 1, 0);
        gl.glRotatef(this.front_left_lower_anglez, 0, 0, 1);
        gl.glTranslatef(this.front_lower_translatex, this.front_lower_translatey, 0);
        gl.glCallList(this.front_left_lower);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
        gl.glRotatef(this.front_left_paw_anglex, 1, 0, 0);
        gl.glRotatef(this.front_left_paw_angley, 0, 1, 0);
        gl.glRotatef(this.front_left_paw_anglez, 0, 0, 1);
        gl.glTranslatef(this.front_paw_translatex, this.front_paw_translatey, 0);
        gl.glCallList(this.front_left_paw);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.front_right_upper_anglex, 1, 0, 0);    
	    gl.glRotatef(this.front_right_upper_angley, 0, 1, 0);
	    gl.glRotatef(this.front_right_upper_anglez, 0, 0, 1);
	    gl.glTranslatef(this.front_upper_translatex, this.front_upper_translatey, 0);
	    gl.glCallList(this.front_right_upper);
    gl.glPopMatrix();    
    gl.glPushMatrix();
	    gl.glRotatef(this.front_right_lower_anglex, 1, 0, 0);
	    gl.glRotatef(this.front_right_lower_angley, 0, 1, 0);
	    gl.glRotatef(this.front_right_lower_anglez, 0, 0, 1);
	    gl.glTranslatef(this.front_lower_translatex, this.front_lower_translatey, 0);
	    gl.glCallList(this.front_right_lower);
    gl.glPopMatrix();
    gl.glPushMatrix();
	    gl.glRotatef(this.front_right_paw_anglex, 1, 0, 0);
	    gl.glRotatef(this.front_right_paw_angley, 0, 1, 0);
	    gl.glRotatef(this.front_right_paw_anglez, 0, 0, 1);
	    gl.glTranslatef(this.front_paw_translatex, this.front_paw_translatey, 0);
	    gl.glCallList(this.front_right_paw);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.rear_left_upper_anglex, 1, 0, 0);
	    gl.glRotatef(this.rear_left_upper_angley, 0, 1, 0);
	    gl.glRotatef(this.rear_left_upper_anglez, 0, 0, 1);
	    gl.glTranslatef(this.rear_upper_translatex, this.rear_upper_translatey, 0);
	    gl.glCallList(this.rear_left_upper);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.rear_left_lower_anglex, 1, 0, 0);
	    gl.glRotatef(this.rear_left_lower_angley, 0, 1, 0);
	    gl.glRotatef(this.rear_left_lower_anglez, 0, 0, 1);
	    gl.glTranslatef(this.rear_lower_translatex, this.rear_lower_translatey, 0);
	    gl.glCallList(this.rear_left_lower);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.rear_left_paw_anglex, 1, 0, 0);
	    gl.glRotatef(this.rear_left_paw_angley, 0, 1, 0);
	    gl.glRotatef(this.rear_left_paw_anglez, 0, 0, 1);
	    gl.glTranslatef(this.rear_paw_translatex, this.rear_paw_translatey, 0);
	    gl.glCallList(this.rear_left_paw);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.rear_right_upper_anglex, 1, 0, 0);  
	    gl.glRotatef(this.rear_right_upper_angley, 0, 1, 0);
	    gl.glRotatef(this.rear_right_upper_anglez, 0, 0, 1);
	    gl.glTranslatef(this.rear_upper_translatex, this.rear_upper_translatey, 0);
	    gl.glCallList(this.rear_right_upper);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.rear_right_lower_anglex, 1, 0, 0);
	    gl.glRotatef(this.rear_right_lower_angley, 0, 1, 0);
	    gl.glRotatef(this.rear_right_lower_anglez, 0, 0, 1);
	    gl.glTranslatef(this.rear_lower_translatex, this.rear_lower_translatey, 0);
	    gl.glCallList(this.rear_right_lower);
    gl.glPopMatrix();
    gl.glPushMatrix();
	    gl.glRotatef(this.rear_right_paw_anglex, 1, 0, 0);
	    gl.glRotatef(this.rear_right_paw_angley, 0, 1, 0);
	    gl.glRotatef(this.rear_right_paw_anglez, 0, 0, 1);
	    gl.glTranslatef(this.rear_paw_translatex, this.rear_paw_translatey, 0);
	    gl.glCallList(this.rear_right_paw);
    gl.glPopMatrix();  
    
    gl.glPushMatrix();
    	gl.glRotatef(this.neck_anglex, 1, 0, 0);    
    	gl.glRotatef(this.neck_angley, 0, 1, 0);
    	gl.glRotatef(this.neck_anglez, 0, 0, 1);
    	gl.glCallList(this.neck);
    gl.glPopMatrix();
    
    gl.glPushMatrix();
	    gl.glRotatef(this.head_anglex, 1, 0, 0);   
	    gl.glRotatef(this.head_angley, 0, 1, 0);   
	    gl.glRotatef(this.head_anglez, 0, 0, 1);
	    gl.glTranslatef(this.head_translatex, this.head_translatey, 0);
	    gl.glCallList(this.head);
    gl.glPopMatrix();    
    
    gl.glPushMatrix();
	    gl.glRotatef(this.tail_anglex, 1, 0, 0); 
	    gl.glRotatef(this.tail_angley, 0, 1, 0); 
	    gl.glRotatef(this.tail_anglez, 0, 0, 1);    
	    gl.glCallList(this.tail);
	gl.glPopMatrix();
  }

  /**
   * Uses the specified OpenGL object to create a new OpenGL call list on which
   * to draw the dog's body, legs, tail, head, and neck.
   * 
   * @param gl
   *          The OpenGL object from which to get a handle (which is just a
   *          unique integer) for a call list.
   */
  public void init(final GL gl) {
    this.dog_object = gl.glGenLists(1);
    this.front_left_upper = gl.glGenLists(1);
    this.front_left_lower = gl.glGenLists(1);
    this.front_left_paw = gl.glGenLists(1);
    this.front_right_upper = gl.glGenLists(1);
    this.front_right_lower = gl.glGenLists(1);
    this.front_right_paw = gl.glGenLists(1);
    this.rear_left_upper = gl.glGenLists(1);
    this.rear_left_lower = gl.glGenLists(1);
    this.rear_left_paw = gl.glGenLists(1);
    this.rear_right_upper = gl.glGenLists(1);
    this.rear_right_lower = gl.glGenLists(1);
    this.rear_right_paw = gl.glGenLists(1);
    this.neck = gl.glGenLists(1);
    this.head = gl.glGenLists(1);
    this.tail = gl.glGenLists(1);
    
    this.update(gl);
  }

  /**
   * Activates the specified type of joint for rotation.
   * 
   * @param joint
   *          The type of joint to rotate.
   */
  public void set_joint(final JointType joint) {
	this.state_has_changed = true;
    switch (joint) {
    case PAW:
      System.out.println(" Paw Chosen ");
      break;
    case UPPER:
      System.out.println(" Upper Chosen ");
      break;
    case LOWER:
      System.out.println(" Lower Chosen ");
      break;
    }
    this.active_joint = joint;
  }

  /**
   * Sets the axis around which to rotate.
   * 
   * @param axis
   *          The axis around which to rotate.
   */
  public void set_rotation_axis(AxisType axis) {
	this.state_has_changed = true;
    switch (axis) {
    case X:
      System.out.println("X-axis chosen");
      break;
    case Y:
      System.out.println("Y-axis chosen");
      break;
    case Z:
      System.out.println("Z-axis chosen");
      break;
    }
    this.active_rotation_axis = axis;
  }

  /**
   * Toggles the specified body part to be affected by rotations.
   * 
   * @param bodyPart
   *          The bodyPart to toggle.
   */
  public void toggle_part(BodyPartType bodyPart) {
	this.state_has_changed = true;
	  
	int loc = 0;

    switch (bodyPart) {
    case REAR_LEFT_LEG:
      loc = 0;
      System.out.println(" Toggling rear left leg");
      break;
    case REAR_RIGHT_LEG:
      loc = 1;
      System.out.println(" Toggling rear right leg");
      break;
    case FRONT_LEFT_LEG:
      loc = 2;
      System.out.println(" Toggling front left leg");
      break;
    case FRONT_RIGHT_LEG:
      loc = 3;
      System.out.println(" Toggling front right leg");
      break;
    case HEAD:
      loc = 4;
      System.out.println(" Toggling head");
      break;
    case TAIL:
      loc = 5;
      System.out.println(" Toggling tail");
      break;
    case NECK:
      loc = 6;
      System.out.println(" Toggling neck");
      break;
    }
    this.active_parts[loc] = !this.active_parts[loc];
  }

  /**
   * Updates the current model of the dog.
   * 
   * @param gl
   *          The OpenGL object with which to draw the dog.
   */
  public void update(final GL gl) {
    if (this.state_has_changed) {
      gl.glNewList(this.dog_object, GL.GL_COMPILE);

      /**
       * You will need to rewrite the following code in order to display the
       * dog with its legs, tail, neck and head.
       */
      
      gl.glColor3f(0.05f, .58f, .78f);
      
      gl.glPushMatrix();
      	// create an ellipsoid by scaling a sphere      	
      	gl.glScalef(1.1f, 0.5f, 0.5f);
        this.glut.glutSolidSphere(1, 36, 18);  
      gl.glPopMatrix();
      
      gl.glEndList();
      	
      drawFrontRightLeg(gl);
      
      drawFrontLeftLeg(gl);	    	    	    	    	    	  
  	    	  
      drawRearLeftLeg(gl);
	  
      drawRearRightLeg(gl);
	  
	  gl.glNewList(this.neck, GL.GL_COMPILE);
      
      //neck
	  gl.glPushMatrix();
	    gl.glRotatef(60f, 0, 0, 1f);
	    gl.glScalef(0.15f, 0.2f, 0.2f);
	    gl.glTranslatef(4.5f, -4f, 0f);  
	    this.glut.glutSolidSphere(1,36,18);
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.head, GL.GL_COMPILE);
	  
	  //head
	  gl.glPushMatrix();
	    gl.glTranslatef(1.35f, .5f, 0f);
	  	this.glut.glutSolidTeapot(.4);
	  	//this.glut.glutSolidSphere(.37,36,18);	  	
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.tail, GL.GL_COMPILE);
	  
	  //tail
	  gl.glPushMatrix();
	  	gl.glRotatef(-45, 0, 0, 1);
	    gl.glScalef(.27f, .09f, .09f);    
	  	gl.glTranslatef(-3.8f, -5.7f, 0f);
	  	this.glut.glutSolidSphere(1,36,18);
	  gl.glPopMatrix();

      gl.glEndList();

      // reset the state_has_changed flag
      this.state_has_changed = false;
    }      
  }
  
  public void drawFrontLeftLeg(final GL gl) {
	  
	  gl.glNewList(this.front_left_upper, GL.GL_COMPILE);	  
	  
	  gl.glPushMatrix();
      gl.glScalef(0.20f, 0.45f, 0.20f);
		// front left
		gl.glPushMatrix();  				
			gl.glTranslatef(3.0f, -0.8f, -1.0f);
			this.glut.glutSolidSphere(1, 36, 18);
		gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.front_left_lower, GL.GL_COMPILE);
 
	  gl.glPushMatrix();
      gl.glScalef(0.11f, 0.20f, 0.11f);
	   // front left
	  	gl.glPushMatrix();  				
		  gl.glTranslatef(5.5f, -4.5f, -2.0f);
		  this.glut.glutSolidSphere(1, 36, 18);
	    gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.front_left_paw, GL.GL_COMPILE);

	  gl.glPushMatrix();
	    gl.glScalef(0.15f, 0.09f, 0.1f);
	   // front left
	  	gl.glPushMatrix();  				
		  gl.glTranslatef(4.8f, -11.9f, -2.1f);
		  this.glut.glutSolidSphere(1, 36, 18);
	    gl.glPopMatrix();   
	  gl.glPopMatrix();  
	  
	  gl.glEndList();
  }
  
  public void drawFrontRightLeg(final GL gl) {
	  
	  gl.glNewList(this.front_right_upper, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
      gl.glScalef(0.20f, 0.45f, 0.20f);
		// Front right
		gl.glPushMatrix();	
			gl.glTranslatef(3.0f, -0.8f, 1.0f);
			this.glut.glutSolidSphere(1, 36, 18);
		gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.front_right_lower, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
      gl.glScalef(0.11f, 0.20f, 0.11f);
		// Front right
		gl.glPushMatrix();	
			gl.glTranslatef(5.5f, -4.5f, 2.0f);
			this.glut.glutSolidSphere(1, 36, 18);
	    gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.front_right_paw, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
	    gl.glScalef(0.15f, 0.09f, 0.1f);
		// Front right
		gl.glPushMatrix();	
			gl.glTranslatef(4.8f, -11.9f, 2.1f);
			this.glut.glutSolidSphere(1, 36, 18);
	    gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
  }
  
  public void drawRearLeftLeg(final GL gl) {
	  
	  gl.glNewList(this.rear_left_upper, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
	    gl.glScalef(0.29f, 0.4f, 0.29f);
	  // back left
	  	gl.glPushMatrix();  				
	      gl.glTranslatef(-2.5f, -0.8f, -1.0f);
	  	  this.glut.glutSolidSphere(1, 36, 18);
	  	gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.rear_left_lower, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
	  	gl.glScalef(0.19f, 0.4f, 0.19f);
	  // back left
	  	gl.glPushMatrix();  				
	  	  gl.glTranslatef(-3.8f, -1.8f, -1.4f);
	      this.glut.glutSolidSphere(1, 36, 18);
	  	gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.rear_left_paw, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
	  	gl.glScalef(0.15f, 0.09f, 0.1f);
    // back left
	    gl.glPushMatrix();  				
		  gl.glTranslatef(-3.8f, -11.9f, -2.9f);
		  this.glut.glutSolidSphere(1, 36, 18);
	    gl.glPopMatrix();
	  gl.glPopMatrix();
	  
	  gl.glEndList();
  }
  
  public void drawRearRightLeg(final GL gl) {
	  
	  gl.glNewList(this.rear_right_upper, GL.GL_COMPILE);
	  
	  gl.glPushMatrix();
	    gl.glScalef(0.29f, 0.4f, 0.29f);
	  // back right
	  	gl.glPushMatrix();     
	  	  gl.glTranslatef(-2.5f, -0.8f, 1.0f);
	  	  this.glut.glutSolidSphere(1, 36, 18);
	  	gl.glPopMatrix();	  			
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.rear_right_lower, GL.GL_COMPILE);
  
	  gl.glPushMatrix();
	  	gl.glScalef(0.19f, 0.4f, 0.19f);
	  // back right
	  	gl.glPushMatrix();     
	  	  gl.glTranslatef(-3.8f, -1.8f, 1.4f);
	  	  this.glut.glutSolidSphere(1, 36, 18);
	  	gl.glPopMatrix();	 
	  gl.glPopMatrix();
	  
	  gl.glEndList();
	  
	  gl.glNewList(this.rear_right_paw, GL.GL_COMPILE);

	  gl.glPushMatrix();
	    gl.glScalef(0.15f, 0.09f, 0.1f);
    // back right
	    gl.glPushMatrix();     
		  gl.glTranslatef(-3.8f, -11.9f, 2.9f);
		  this.glut.glutSolidSphere(1, 36, 18);
	    gl.glPopMatrix();	 
	  gl.glPopMatrix();
	  
	  gl.glEndList();
  }

  public void reset() {
		// TODO Auto-generated method stub
		body_anglex = 0;
		body_angley = 0;
		body_anglez = 0;
		front_left_upper_anglex = 0;
		front_left_upper_angley = 0;
		front_left_upper_anglez = 0;
		front_left_lower_anglex = 0;
		front_left_lower_angley = 0;
		front_left_lower_anglez = 0;
		front_left_paw_anglex = 0;
		front_left_paw_angley = 0;
		front_left_paw_anglez = 0;
		front_right_upper_anglex = 0;
		front_right_upper_angley = 0;
		front_right_upper_anglez = 0;
		front_right_lower_anglex = 0;
		front_right_lower_angley = 0;
		front_right_lower_anglez = 0;
		front_right_paw_anglex = 0;
		front_right_paw_angley = 0;
		front_right_paw_anglez = 0;
		rear_left_upper_anglex = 0;
		rear_left_upper_angley = 0;
		rear_left_upper_anglez = 0;
		rear_left_lower_anglex = 0;
		rear_left_lower_angley = 0;
		rear_left_lower_anglez = 0;
		rear_left_paw_anglex = 0;
		rear_left_paw_angley = 0;
		rear_left_paw_anglez = 0;
		rear_right_upper_anglex = 0;
		rear_right_upper_angley = 0;
		rear_right_upper_anglez = 0;
		rear_right_lower_anglex = 0;
		rear_right_lower_angley = 0;
		rear_right_lower_anglez = 0;
		rear_right_paw_anglex = 0;
		rear_right_paw_angley = 0;
		rear_right_paw_anglez = 0;
		neck_anglex = 0;
		neck_angley = 0;
		neck_anglez = 0;
		head_anglex = 0;
		head_angley = 0;
		head_anglez = 0;
		tail_anglex = 0;
		tail_angley = 0;
		tail_anglez = 0;
		rear_upper_translatex = (float) 0;
		rear_upper_translatey = (float) 0;
		rear_lower_translatex = (float) 0;
		rear_lower_translatey = (float) 0;
		front_upper_translatex = (float) 0;
		front_upper_translatey = (float) 0;
		front_lower_translatex = (float) 0;
		front_lower_translatey = (float) 0;
		front_paw_translatex = (float) 0;
		front_paw_translatey = (float) 0;
		rear_paw_translatex = 0;
		rear_paw_translatey = 0;
		head_translatex = (float) 0;
		head_translatey = (float) 0;
  }

  public void test() {
		// TODO Auto-generated method stub
		reset();
		if (command % 5 == 0) { // sit
			System.out.println("sit");
			body_anglez = 40;
			rear_left_upper_anglez = 65;
			rear_left_lower_anglez = -60;
			rear_left_paw_anglez = 10;
			rear_right_upper_anglez = 65;
			rear_upper_translatex = (float) 0.1;
			rear_upper_translatey = (float) 0.35;
			rear_lower_translatex = (float) 1.35;
			rear_right_lower_anglez = -60;
			rear_right_paw_anglez = 10;
			neck_anglez = 30;
			head_translatex = (float) -0.2;
			head_translatey = (float) 0.5;
			tail_anglez = 40;
		} else if (command % 5 == 1) { // roll over
			System.out.println("roll over");
			body_anglez = 180;
			front_left_upper_anglez = 150;
			front_left_lower_anglez = 210;
			front_left_paw_anglez = 180;
			front_right_upper_anglez = 150;
			front_right_lower_anglez = 210;
			front_right_paw_anglez = 180;
			rear_left_upper_anglez = 180;
			rear_left_lower_anglez = 200;
			rear_left_paw_anglez = 180;
			rear_right_upper_anglez = 180;
			rear_right_lower_anglez = 210;
			rear_right_paw_anglez = 185;
			neck_anglez = 180;
			head_anglez = 170;
			head_anglex = -10;
			head_angley = -12;
			tail_anglez = 180;
			rear_upper_translatex = (float) 0;
			rear_upper_translatey = (float) 0;
			rear_lower_translatex = (float) -0.2;
			rear_lower_translatey = (float) 0.3;
			front_upper_translatex = (float) 0.2;
			front_upper_translatey = (float) 0.3;
			front_lower_translatex = (float) -0.52;
			front_lower_translatey = (float) -0.2;
			front_paw_translatex = (float) -0.15;
			front_paw_translatey = (float) 0;
			rear_paw_translatex = (float) -0.15;
			rear_paw_translatey = (float) 0;
			head_translatex = (float) 0;
			head_translatey = (float) 0;
		} else if (command % 5 == 2) { // shake hands
			System.out.println("shake hands");
			body_anglez = 40;
			front_right_upper_anglez = 45;
			front_right_lower_anglez = 45;
			front_right_paw_anglez = 45;
			rear_left_upper_anglez = 65;
			rear_left_lower_anglez = -60;
			rear_left_paw_anglez = 10;
			rear_right_upper_anglez = 65;
			rear_upper_translatex = (float) 0.1;
			rear_upper_translatey = (float) 0.35;
			rear_lower_translatex = (float) 1.35;
			rear_right_lower_anglez = -60;
			rear_right_paw_anglez = 10;
			neck_anglez = 30;
			head_translatex = (float) -0.2;
			head_translatey = (float) 0.5;
			tail_anglez = 40;
		} else if (command % 5 == 3) { // beg
			System.out.println("beg");
			body_anglez = 60;
			front_left_upper_anglez = 60;
			front_left_lower_anglez = 58;
			front_left_paw_anglez = -40;
			front_right_upper_anglez = 60;
			front_right_lower_anglez = 58;
			front_right_paw_anglez = -40;
			rear_right_upper_anglez = 65;
			rear_right_lower_anglez = -60;
			rear_right_paw_anglez = 10;
			neck_anglez = 50;
			head_anglez = 20;	
			head_translatex = (float) -0.1;
			head_translatey = (float) 0.5;
			rear_left_upper_anglez = 65;
			rear_left_lower_anglez = -60;
			rear_left_paw_anglez = 10;	
			rear_upper_translatex = (float) 0.1;
			rear_upper_translatey = (float) 0.30;
			rear_lower_translatex = (float) 1.35;
			front_paw_translatex = (float) 0.4;
			front_paw_translatey = (float) 1.8;
			tail_anglez = 60;
		} else if (command % 5 == 4) {
			System.out.println("jump");
			front_left_upper_anglez = 60;
			front_left_lower_anglez = 90;
			front_right_upper_anglez = 60;
			front_right_lower_anglez = 90;
			rear_left_upper_anglez = -70;
			rear_left_lower_anglez = -67;
			rear_left_paw_anglez = 10;
			rear_right_upper_anglez = -70;
			rear_right_lower_anglez = -67;
			rear_right_paw_anglez = 10;
			tail_anglez = 18;
			rear_upper_translatex = (float) 0.5;
			rear_upper_translatey = (float) -0.5;
			rear_lower_translatex = (float) 0.5;
			rear_lower_translatey = (float) -0.5;
			front_upper_translatex = (float) -0.2;
			front_upper_translatey = (float) -0.5;
			front_lower_translatex = (float) -0.85;
			front_lower_translatey = (float) -0.5;
			front_paw_translatex = (float) 0.9;
			front_paw_translatey = (float) 0.8;
			rear_paw_translatex = (float) -1.1;
			rear_paw_translatey = (float) 0.9;
		}					
		command++;
	}

}
