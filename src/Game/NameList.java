package Game;

import java.util.ArrayList;

public class NameList {
	String[] names = {"Abies", "Amabilis", "Balsamea", "Bracteata", "Fraseri", "Lasiocarpa", "Magnifica", "Procera", "Acer", "Macrophyllum",
			"Negundo", "Pensylvanicum", "Rubrum", "Saccharinum", "Saccharum", "Aesculus", "Californica", "Ague", "Tree", "Ahuehuete", 
			"Cedar", "Cypress", "Yellowcedar", "Larch", "Alberta", "Spruce", "White", "Allegheny", "Chinkapin", "Almond", "Willow",
			"Almondleaf", "Alnus", "Incana", "Subsp.", "Rugosa", "Tenuifolia", "Rhombifolia", "Rubra", "Viridis", "Crispa", "Sinuata", "Alpine",
			"Fir", "Hemlock", "Amelanchier", "Arborea", "Basswood", "Beech", "Elm", "Green", "Alder", "Hackberry", "Holly", 
			"Hophornbeam", "Hornbeam", "Plum", "Sycamore", "Walnut", "Angelica", "Apache", "Pine", "Aralia", "Spinosa", "Arbor", "Vitae", 
			"Arborvitae", "Arbutus", "Arizonica", "Menziesii", "Texana", "Arce", "Black", "Longleaf", "Madrone",
			"Madrono", "Oak", "Rough", "Soft", "Maple", "Ashe", "Juniper", "Ashleaf", "Asimina", "Triloba", "Aspen", "Athel", 
			"Tamarisk", "Atlantic", "Austrian", "Baker", "Baldcypress", "Balsam", "Fraser", "Poplar", "Banks", "Banksian", "Barren", "Basket",
			"Bay", "Laurel", "Baytree", "Beach", "Beak", "Beaked", "Bearberry", "Beaverwood", "Bebb", "Bellota", "Betula", "Alleghaniensis", "Nigra",
			"Occidentalis", "Papyrifera", "Populifolia", "Big", "Drunk", "Bean", "Bigcone", "Bigleaf", "Bigtooth", "Bigtree", "Biltmore", "Ash", "Bishop",
			"Bitter", "Cherry", "Bitternut", "Hickory", "Birch", "Birck", "Hills", "Jack", "Locust", "Myrtle", "Tupelo", "Blackgum",
			"Blackjack", "Blisted", "Blister", "Blue", "Paloverde", "Bluegum", "Eucalyptus", "Bluejack", "Bois", "D'arc", "Border", "Limber", "Pinyon",
			"Bottom", "Post", "Bottomland", "Red", "Boxelder", "Boynton", "Bracted", "Brake", "Break", "Brewer", "Bristlecone", "Broadleaf", "Broom",
			"Buckeye", "Bull", "Bullnut", "Bur", "Butternut", "Buttonball", "Cabbage", "Palmetto", "California", "Coulter", "Fan", "Palm", "Filbert",
			"Hazel", "Hazelnut", "Nutmeg", "Redwood", "Swamp", "Torreya", "Calocedrus", "Decurrens", "Cambria", "Canada", "Canadian", "Cane", 
			"Canyon", "Live", "Carpinus", "Caroliniana", "Carya", "Cordiformis", "Glabra", "Illinoensis", "Ovata", "Tomentosa", "Cascades",
			"Castanea", "Pumila", "Casuarina", "Caudate", "Cedro", "Blanco", "De", "La", "Sierra", "Cedros", "Island", "Celtis",
			"Laevigata", "Reticulata", "Cercidium", "Floridum", "Cercis", "Canadensis", "Chamaecyparis", "Lawsoniana", "Nootkatensis", "Thyoides",
			"Chaton", "Cherioni", "Cherrion", "Cherrybark", "Chestnut", "Chinaberry", "Chinquapin", "Chrysolepis", "Chrysophylla",
			"Cinnamon", "Bush", "Wood", "Cipres", "Ciruela", "Coast", "Pignut", "Coastal",  "Colorin", "Columbian", "Common", "Persimmon",
			"Sassafras", "Coos", "Copalm", "Coral", "Coralbean", "Corkbark", "Cormier", "Cornus", "Racemosa", "Corsican", "Corylus",
			"Cornuta", "Crataegus", "Douglasii", "Cross", "Cunningham", "Cupressus", "Bakeri", "Forbesii", "Goveniana",
			"Macnabiana", "Macrocarpa", "Sargentii", "Curtiss", "Possumhaw", "Custard", "Apple", "Dade", "County", "Slash", "Darlington", "Deciduous",
			"Del", "Mar", "Delta", "Desert", "Walking", "Stick", "Diamond", "Diospyros", "Virginiana", "Banana", "Dogberry", "Downy",
			"Serviceberry", "Drummond", "Soapberry", "Duck", "Dudley", "Dune", "Dwarf", "Redbud", "Elliott", "Emory", "Enebro",
			"Engelmann", "Globulus", "European", "Evergreen", "Magnolia", "Fagus", "Grandifolia", "Acacia", "Shagbark", "Fire",
			"Yew", "Forbes", "Fraxinus", "Americana", "Fresno", "Guajuco", "Frigolito", "Frijolillo", "Frijolito",
			"Frijollito", "Giant", "Redcedar", "Sequoia", "Ginger", "Glaucous", "Gleditsia", "Triacanthos", "Golden", "Goldenleaf", "Goodding",
			"Goosefoot", "Gopherwood", "Gordonia", "Lasianthus", "Gowen", "Gray", "Dogwood", "Grayleaf", "Grey", "Sheoak", "Guadalupe", "Gulf",
			"Gumbo", "File", "Hackmatack", "Hamamelis", "Hard", "Highland", "Hoary", "Hognut", "Hojalito", "Honey",
			"Shucks", "Horestail", "Horsechestnut", "Hudson", "Hummock", "Idaho", "Ilex", "Decidua", "Opaca", "Nut", "Indian", "Soap",
			"Plant", "Inland", "Insignis", "Iron", "Ironwood", "Jaboncillo", "Jeffrey", "Jersey", "Juglans", "Cinerea", "Major", "Microcarpa",
			"Juneberry", "Juniperus", "Ashei", "Coahuilensis", "Knobcone", "Knowlton", "Largetooth", "Larix", "Laricina",
			"Lyallii", "Lawson", "Linden", "Liquidambar", "Styraciflua", "Liriodendron", "Tulipifera", "Lithocarpus", "Densiflorus", "Little", "Loblolly",
			"Lodgepole", "Lovely", "Lyall", "MacNab", "Maclura", "Pomifera", "Madrona", "Grandiflora", "Manitoba", "Manzanita",
			"Maul", "Menzies'", "Mescal", "Sophora", "Mescalbean", "Mockernut", "Modoc", "Monterey",
			"Montezuma", "Moosewood", "Morus", "Mossycup", "Mahogany", "Mulberry", "Muscletree", "Myrtletree", "Myrtlewood",
			"Namboca", "Netleaf", "Nettletree", "Noble", "Nogal", "Nogalillo", "Nogalito", "Nootka", "Pin",
			"Scrub", "Nyssa", "Sylvatica", "Obtusa", "Oilnut", "Old", "Field", "Oldfield", "Orange", "Oregon", "Ostrya", "Knowltonii",
			"Overcup", "Oxydendrum", "Arboreum", "Ozark", "Pacific", "Ponderosa", "Silver", "Palo", "Panicled", "Paper", "Papershell", "Parry", 
			"Pawpaw", "Peach", "Peachleaf", "Pecan", "Pentamon", "Pepperidge", "Pepperwood", "Persea", "Borbonia", "Petit", "Minou", "Picea",
			"Breweriana", "Engelmannii", "Glauca", "Pungens", "Rubens", "Sitchensis", "Pick", "Blanc", "Pino", "Enano", "Real", "Pinobete", "Pinus",
			"Attenuata", "Banksiana", "Cembroides", "Clausa", "Contorta", "Murrayana", "Coulteri", "Echinata", "Elliottii", "Jeffreyi", "Lambertiana",
			"Leiophylla", "Chihuahuana", "Monticola", "Muricata", "Quadrifolia", "Radiata", "Resinosa", "Sabiniana", "Strobiformis", "Strobus",
			"Sylvestris", "Taeda", "Torreyana", "Washoensis", "Pitch", "Plane", "Platanus", "Polecat", "Pondcypress", "Populus", "Balsamifera",
			"Grandidentata", "Tremuloides", "Porsild", "Port", "Possum", "Possumwood", "Pottawattami", "Poverty", "Prickly", "Elder", "Princess",
			"Prosopis", "Velutina", "Prunus", "Emarginata", "Serotina", "Pseudotsuga", "Punk", "Pyrenees", "Quaking", "Quercitron", "Quercus", "Alba",
			"Bicolor", "Coccinea", "Ellipsoidalis", "Emoryi", "Falcata", "Grisea", "Laurifolia", "Lobata", "Lyrata", "Marilandica", "Michauxii",
			"Muehlenbergii", "Oblongifolia", "Palustris", "Phellos", "Prinus", "Shumardii", "Stellata", "Wislizenii", "Redbay", "Redberry", "Redgum",
			"Rhus", "Typhina", "Ridge", "River", "Robinia", "Neomexicana", "Pseudoacacia", "Roble", "Rock", "Rocky", "Roundwood", "Rum", "Runner",
			"Sabal", "Sabino", "Salix", "Amygdaloides", "Bebbiana", "Gooddingii", "Lasiandra", "Lutea", "Saloop", "Sand", "Sandhill", "Sandjack",
			"Sandpaper", "Santa", "Lucia", "Rosa", "Torrey", "Sapgum", "Sapindus", "Saponaria", "Drummondii", "Sargent", "Albidum", "Savin", "Scalybark",
			"Scarlet", "Schneck", "Scots", "Scrubby", "Sempervirens", "Sequoiadendron", "Giganteum", "Shadblow", "Shadbush", "Shasta", "Shellbark",
			"Shin", "Shinglewood", "Shipmast", "Shore", "Shorebay", "Shortleaf", "Yellow", "Shortstraw", "Shotbush", "Shumard", "Silkbay",
			"Silvertip", "Simmon", "Siskiyou", "Sitka", "Skunk", "Slippery", "Smelling", "Smooth", "Smoothbark", "Berry", "Soledad", "Secundiflora",
			"Sorbus", "Sourgum", "Sourwood", "South", "Southern", "Sweetbay", "Southwestern", "Spanish", "Spotted", "Spring", "Staghorn", "Sumac",
			"Gum", "Stave", "Stinking", "Striped", "Subalpine", "Sugar", "Sugarberry", "Sugarplum", "Swampbay", "Sweet", "Sweetgum", 
			"Tamarack", "Tamarix", "Aphylla", "Tan", "Tanbark", "Tanoak", "Tascate", "Tasmanian", "Taxodium", "Distichum", "Mucronatum", "Taxus",
			"Brevifolia", "Floridana", "Tecate", "Texate", "Thinleaf", "Thuja", "Plicata", "Tideland", "Tilia", "Timberline", "Toothache",
			"Taxifolia", "Trembling", "Tsuga", "Heterophylla", "Mertensiana", "Tuliptree", "Ulmus", "Umbellularia", "Una", "Gato", "Vasey",
			"Velvet", "Mesquite", "Washingtonia", "Filifera", "Washoe", "Water", "Wavyleaf",
			"Weeping", "West", "Western", "Yellowpine", "Weymouth", "Whiplash", "Whistlewood", "Whistling", "Whiteheart", "Wild", "Chinatree",
			"Winterberry", "Wire", "Wolf", "Woolly", "Wright", "Butt", "Yellowbark", "Brush"};

//	String[] words = names;//.split(" ");
//	public NameList(){
//		ArrayList<String> result = new ArrayList<String>();
//		for(int i = 0; i<words.length;i++){
//			if(!result.contains(words[i])&&!words[i].contains("-")&&words[i].length()>1){
//				result.add(words[i]);
//			}
//		}
//		String endResult = "names = {\"";
//		for(int i = 0; i<result.size();i++){
//			endResult = endResult+result.get(i);
//			if(i!=result.size()-1){
//				endResult = endResult+"\", \"";
//			}
//			else{
//				endResult = endResult+"\"};";
//			}
//		}
//		System.out.println(endResult);
//	}
}
