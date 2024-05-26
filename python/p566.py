from math import sqrt

def get_affected_areas(areas:list,angle:float,start_angle:float=0.0)->tuple:
    """function that from a list of areas splits into affected and unaffected areas"""
    areas_affected=[]
    areas_new=[]

    for area in areas:
        ## 11 area completely in target space
        if start_angle<=area[0]<area[1]<=angle:
            areas_affected.append(area)

        ## 12 area with upperbound in target space
        elif start_angle<area[1]<=angle:
            ar_out,ar_in=(area[0],start_angle),(start_angle,area[1])
            areas_new.append(ar_out)
            areas_affected.append(ar_in)

        ## 13 area with lowerbound in target space
        elif start_angle<=area[0]<angle:
            ar_in,ar_out=(area[0],angle),(angle,area[1])
            areas_new.append(ar_out)
            areas_affected.append(ar_in)

        ## 14 area engulfing targetspace
        elif start_angle<angle<area[1]<area[0]:
            ar_in,ar_out_lower,ar_out_upper=(start_angle,angle),(area[0],start_angle),(angle,area[1])
            areas_new.append(ar_out_lower)
            areas_new.append(ar_out_upper)
            areas_affected.append(ar_in)

        ## 15 uneffected area
        else:
            areas_new.append(area)

    return areas_affected,areas_new

def rotate_num(value:float, center:float)->float:
    """function to rotate values around center, when a piece of cake is flipped."""
    return center*2-value

def get_pos_area(area:tuple, angle:float, angle_start:float=0.0)->tuple:
    """get the positive area values beloning to a negative area"""
    center=(angle+angle_start)/2
    return (rotate_num(area[1],center),rotate_num(area[0],center))

def flip_affected_areas(areas_affected:list,angle:float,start_angle:float=0.0)->list:
    """
    function that takes a list of negative areas.
    eturns the negative areas after the region has been flipped.
    """
    ## 21 get new positive areas
    area_affected_positive=[]

    # get all areas, that are not negative in the target area.
    # these (to be precise their rotated values) are the ones that will become negative after being flipped
    for ea in areas_affected:
        ar_pos=get_pos_area(area=ea,angle=angle,angle_start=start_angle)
        area_affected_positive.insert(0,ar_pos)

    ## 22 get negative areas from positives -- if no areas, all negative
    areas_affected_flipped=[]
    pos=start_angle

    for ap in area_affected_positive:
        areas_affected_flipped.append((pos,ap[0]))
        pos=ap[1]

    try:
        areas_affected_flipped.append((area_affected_positive[-1][1],angle))
        ## 23 deleting empty areas
        areas_affected_flipped=[an for an in areas_affected_flipped if an[0]!=an[1]]

    except (IndexError):
        # this error happens if area_affected_positive is empty sinc there exist no positive areas.
        # this is not problematic, as in this case no areas are appended to areas_new.
        areas_affected_flipped.append((start_angle,angle))

    return areas_affected_flipped


def sort_pieces(piece)->float:
    """function for sorting pieces based on second value (upper bound)"""
    return piece[1]

def f(
    a:int,
    b:int,
    c:int,
    max_counter:int=1_269_260,
    log_threshold:int=1e6,
    epsilon:float=1e-10
    )->int:
    """"
    function to run a while loop of rotating cake pieces of sizes a,b,c cyclically
    returns number of steps needed until the upperside of the cake is on top again
    max_counter set by default to 1.2M as this is the expected result of g(17)
    """

    # 0 Initialize an empty vector to store negative areas.
    # all negative areas will be collected and deducted based on cake piece rotation
    # when the list areas is empty this means there exist no negative areas
    # hence then the cake is perfectly upside up
    areas=[]

    # angle length
    angles=(360/a,360/b,360/sqrt(c))

    # counter of the iterations. will also be used to change the angles applied at each step
    counter=0

    # start angle is always at 0, since the cake will be rotated. this drastically reduces complexity
    start_angle=0.0

    # termination criteria for while loop
    cake_unfinished=True

    while cake_unfinished:
        # get current angle
        angle=angles[counter%3]

        # # 1 identify all affected areas as well as uneffected areas and put into new arrays
        areas_affected,areas_new=get_affected_areas(
            areas=areas,
            angle=angle,
            start_angle=start_angle
            )

        # 2 flip affected areas
        areas_affected_flipped=flip_affected_areas(
            areas_affected=areas_affected,
            angle=angle,
            start_angle=start_angle
            )

        # 3 join effected and uneffected areas
        areas_new+=areas_affected_flipped

        # 4 rotate cake (north the cake)

        ## 41 add degrees (cake needs to be rotated the amount of the next angle)
        angle_rotate=angles[(counter+1)%3]
        areas_new=[((a[0]+angle_rotate)%360,(a[1]+angle_rotate)%360) for a in areas_new]

        ## 42 sort for next iteration
        areas_new.sort(key=sort_pieces)

        # 5 update for next iteration

        ## 51 throw out "tiny" areas, as they occur due to rounding issues
        areas=[a for a in areas_new if abs(a[0]-a[1])>epsilon]

        ## 52 count up for next iteration
        counter+=1

        # give periodical logs to confirm program is still running
        if (counter%log_threshold)==0:
            print(f"cake {a,b,c} at counter {counter}")
            pass

        # checkout when cake is finished
        if len(areas)==0:
            cake_unfinished=False
            print(f"cake {a,b,c} finalized after {counter} iterations!")
            return counter
        # checkout in case cake does not finish in time
        elif counter>max_counter:
            cake_unfinished=False
            print(f"cake not finilized after {counter} iterations!")
            return 0

# tests for f
assert f(a=9,b=10,c=11)==60
assert f(a=10,b=14,c=16)==506
assert f(a=15,b=16,c=17)==785_232

def g(
    upper_bound:int,
    lower_bound:int=9,
    max_counter:int=1269260,
    log_threshold:int=1e6,
    epsilon:float=1e-10
    )->int:
    """function iterating over all possible combinations of f() within specified upper_bound """
    return sum([f(a=a,b=b,c=c,max_counter=max_counter,log_threshold=log_threshold,epsilon=epsilon) for a in range(lower_bound, upper_bound-1) for b in range(a+1,upper_bound) for c in range(b+1,upper_bound+1)])

# tests for g
assert g(upper_bound=11)==60
assert g(upper_bound=14)==58_020
assert g(upper_bound=17)==1_269_260

print(g(53))
