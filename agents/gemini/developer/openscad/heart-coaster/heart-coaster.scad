
/* 
   Heart Pattern Coaster
   Inspired by Mike Kasberg's repeating pattern coasters.
*/

// Set global resolution for smooth curves
$fn = 60; 

// --- MODULE DEFINITION ---
module heart_coaster(
    coaster_radius = 45,     // Outer radius of the bounding circle (90mm diameter)
    coaster_height = 4,      // Total height/thickness of the coaster
    text_str = "Robert"      // Center label text
) {
    // Internal layout parameters
    base_thickness = 1.2;    // Thickness of the solid bottom layer
    rim_width = 3;           // Width of the outer circular border
    heart_size = 5.5;        // Size of the individual hearts
    spacing = 13;            // Distance between hearts (larger than size = no touching)
    center_clearance = 26;   // Radius of the blank area cut out for the text
    text_size = 11;          // Font size

    // 1. Create the solid base layer
    cylinder(r = coaster_radius, h = base_thickness, $fn = 100);

    // 2. Create the upper layer (rim, pattern, and text) all in 2D, then extrude them
    linear_extrude(height = coaster_height) {
        
        // --- OUTER RIM ---
        difference() {
            circle(r = coaster_radius, $fn = 100);
            circle(r = coaster_radius - rim_width, $fn = 100);
        }

        // --- REPEATING HEART PATTERN ---
        difference() {
            // Keep hearts only inside the coaster rim
            intersection() {
                // Inner boundary
                circle(r = coaster_radius - rim_width + 0.1, $fn = 100);
                
                // Grid generator for the hearts
                for (x = [-coaster_radius : spacing : coaster_radius]) {
                    for (y = [-coaster_radius : spacing : coaster_radius]) {
                        // Offset every other row for an organic, staggered honeycomb look
                        offset_x = (round(y / spacing) % 2 == 0) ? 0 : spacing / 2;
                        
                        translate([x + offset_x, y]) {
                            heart_2d(heart_size);
                        }
                    }
                }
            }
            
            // Cut out the center so the text is easy to read
            circle(r = center_clearance, $fn = 100);
        }

        // --- CENTER TEXT LABEL ---
        // The text will be perfectly flush with the height of the coaster
        text(
            text = text_str, 
            size = text_size, 
            font = "Arial:style=Bold", 
            valign = "center", 
            halign = "center"
        );
    }
}

// --- HELPER MODULE: 2D HEART ---
// Creates a simple 2D heart using a square and two circles
module heart_2d(size) {
    // Translate slightly on Y to center the visual mass of the heart
    translate([0, -size / 1.5, 0]) {
        rotate(45) {
            union() {
                // Main body
                square(size);
                // Left lobe (top edge of rotated square)
                translate([size / 2, size]) circle(r = size / 2);
                // Right lobe (right edge of rotated square)
                translate([size, size / 2]) circle(r = size / 2);
            }
        }
    }
}

// --- RENDER THE COASTER ---
// You can easily change the parameters here!
heart_coaster(
    coaster_radius = 45, 
    coaster_height = 4, 
    text_str = "Robert"
);
